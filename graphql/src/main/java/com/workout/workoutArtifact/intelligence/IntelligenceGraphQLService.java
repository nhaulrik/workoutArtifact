package com.workout.workoutArtifact.intelligence;

import com.workout.workoutArtifact.application.intelligence.ExerciseIntelligence;
import com.workout.workoutArtifact.application.intelligence.WorkoutExerciseIntelligence;
import com.workout.workoutArtifact.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.intelligence.dto.SessionIntelligenceDto;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntelligenceGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final WorkoutExerciseIntelligence workoutExerciseIntelligence;

  @GraphQLQuery(name = "exerciseIntelligence")
  public ExerciseIntelligence getExerciseIntelligence(
      @GraphQLArgument(name = "userId") UUID userId,
      @GraphQLArgument(name = "exerciseIds") List<UUID> exerciseIds,
      @GraphQLArgument(name = "fromDateString") String fromDateString,
      @GraphQLArgument(name = "toDateString") String toDateString,
      @GraphQLArgument(name = "sessionsBack") Integer sessionsBack
  ) {
    return workoutExerciseIntelligence.getAverages(userId, sessionsBack, exerciseIds);
  }

  @GraphQLQuery(name = "sessionIntelligence")
  public List<SessionIntelligenceDto> getSessionIntelligenceBySessionId(
      @GraphQLArgument(name = "userIds") List<UUID> userIds
  ) {

    List<SessionIntelligenceDto> sessionIntelligenceDtos = new ArrayList<>();

//    if (userIds != null) {
//      List<Session> sessions = sessionEntityRepository.getSessions(new UserIdsSpecification(userIds));
//      sessions.forEach(session -> {
//        AtomicReference<Integer> totalRepetitions = new AtomicReference<>(0);
//        AtomicReference<Double> totalWeight = new AtomicReference<>(0d);
//
//        session.getWorkoutExercises().stream()
//            .map(WorkoutExercise::getWorkoutSetIds)
//            .flatMap(Collection::stream)
//            .forEach(workoutSet -> {
//              totalRepetitions.updateAndGet(v -> v + workoutSet.getRepetitions());
//              totalWeight.updateAndGet(v -> v + (workoutSet.getWeight() * workoutSet.getRepetitions()));
//            });
//
//        sessionIntelligenceDtos.add(SessionIntelligenceDto.builder()
//            .totalRepetitions(totalRepetitions.get())
//            .totalWeight(totalWeight.get())
//            .dateTime(session.getCreationDateTime())
//            .build());
//      });
//    }

    return sessionIntelligenceDtos.stream()
        .sorted(Comparator.comparing(SessionIntelligenceDto::getDateTime, Comparator.nullsLast(Comparator.reverseOrder())))
        .collect(Collectors.toList());
  }

}
