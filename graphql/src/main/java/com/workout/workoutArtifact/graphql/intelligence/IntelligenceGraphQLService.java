package com.workout.workoutArtifact.graphql.intelligence;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.Session.BetweenDateTimeSpecification;
import com.workout.workoutArtifact.domain.session.model.Session.UserIdsSpecification;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.graphql.helper.DateHelper;
import com.workout.workoutArtifact.graphql.intelligence.dto.ExerciseIntelligenceDto;
import com.workout.workoutArtifact.graphql.intelligence.dto.ExerciseIntelligenceDto.ExerciseAverage;
import com.workout.workoutArtifact.graphql.intelligence.dto.SessionIntelligenceDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.SessionEntityRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntelligenceGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionEntityRepository sessionEntityRepository;

  @GraphQLQuery(name = "exerciseIntelligence")
  public ExerciseIntelligenceDto getExerciseIntelligence(
      @GraphQLArgument(name = "userId") UUID userId,
      @GraphQLArgument(name = "exerciseIds") List<UUID> exerciseIds,
      @GraphQLArgument(name = "fromDateString") String fromDateString,
      @GraphQLArgument(name = "toDateString") String toDateString
  ) {
      List<AbstractSpecification<Session>> sessionSpecifications = new ArrayList<>();
      AbstractSpecification aggregatedSpecification;

      sessionSpecifications.add(new UserIdsSpecification(Arrays.asList(userId)));

      if (fromDateString != null && toDateString != null) {
        LocalDateTime fromDate = DateHelper.parseDateFromString(fromDateString);
        LocalDateTime toDate = DateHelper.parseDateFromString(toDateString);
        sessionSpecifications.add(new BetweenDateTimeSpecification(fromDate, toDate));
      }

      aggregatedSpecification = sessionSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

      List<Session> sessions = sessionEntityRepository.getSessions(aggregatedSpecification);
      List<ExerciseAverage> exerciseAverages = new ArrayList<>();

      Map<String, List<WorkoutSet>> workoutSetMap = new HashMap<>();

      List<WorkoutExercise> allWorkoutExercises = sessions.stream()
          .map(Session::getWorkoutExercises)
          .flatMap(Collection::stream)
          .filter(we -> !we.getIsWarmup())
          .collect(Collectors.toList());

      if (exerciseIds != null) {
        allWorkoutExercises.removeIf(workoutExercise -> !exerciseIds.contains(workoutExercise.getExercise().getId()));
      }

      allWorkoutExercises.forEach(workoutExercise -> workoutSetMap.put(workoutExercise.getExercise().getName(), new ArrayList<>()));
      allWorkoutExercises.forEach(workoutExercise -> workoutSetMap.get(workoutExercise.getExercise().getName()).addAll(workoutExercise.getWorkoutSets()));

      workoutSetMap.forEach((exerciseName, workoutSetList) -> exerciseAverages.add(new ExerciseAverage(
              exerciseName,
              workoutSetList.stream().map(WorkoutSet::getWeight).collect(Collectors.averagingDouble(Double::doubleValue)),
              workoutSetList.size()
          ))
      );

      return ExerciseIntelligenceDto.builder()
          .userId(userId)
          .exerciseAverages(exerciseAverages)
          .build();
  }

  @GraphQLQuery(name = "sessionIntelligence")
  public List<SessionIntelligenceDto> getSessionIntelligenceBySessionId(
      @GraphQLArgument(name = "userIds") List<UUID> userIds
  ) {

    List<SessionIntelligenceDto> sessionIntelligenceDtos = new ArrayList<>();

    if (userIds != null) {
      List<Session> sessions = sessionEntityRepository.getSessions(new UserIdsSpecification(userIds));
      sessions.forEach(session -> {
        AtomicReference<Integer> totalRepetitions = new AtomicReference<>(0);
        AtomicReference<Double> totalWeight = new AtomicReference<>(0d);

        session.getWorkoutExercises().stream()
            .map(WorkoutExercise::getWorkoutSets)
            .flatMap(Collection::stream)
            .forEach(workoutSet -> {
              totalRepetitions.updateAndGet(v -> v + workoutSet.getRepetitions());
              totalWeight.updateAndGet(v -> v + (workoutSet.getWeight() * workoutSet.getRepetitions()));
            });

        sessionIntelligenceDtos.add(SessionIntelligenceDto.builder()
            .totalRepetitions(totalRepetitions.get())
            .totalWeight(totalWeight.get())
            .dateTime(session.getCreationDateTime())
            .build());
      });
    }

    return sessionIntelligenceDtos.stream()
        .sorted(Comparator.comparing(SessionIntelligenceDto::getDateTime, Comparator.nullsLast(Comparator.reverseOrder())))
        .collect(Collectors.toList());
  }

}
