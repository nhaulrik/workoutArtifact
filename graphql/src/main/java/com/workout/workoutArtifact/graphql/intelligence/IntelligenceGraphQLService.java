package com.workout.workoutArtifact.graphql.intelligence;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.Session.UserIdsSpecification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.graphql.intelligence.dto.SessionIntelligenceDto;
import com.workout.workoutArtifact.graphql.fetcher.SessionFetcher;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.SessionEntityRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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

  private final SessionFetcher sessionFetcher;
  private final SessionEntityRepository sessionEntityRepository;

//  @GraphQLQuery(name = "sessionIntelligence")
//  public List<SessionIntelligenceDto> getSessionIntelligenceByUserId(
//      @GraphQLArgument(name = "userIds") List<UUID> userIds
//  ) {
//    List<SessionIntelligenceDto> sessionIntelligenceDtos = new ArrayList<>();
//
//    if (userIds != null) {
//      userIds.forEach(userId -> {
//            List<SessionDto> sessions = sessionFetcher.getSessions(new UserIdsSpecification(Arrays.asList(userId)));
//            sessionIntelligenceDtos.add(SessionIntelligenceDto.builder().userId(userId).count(sessions.size()).build());
//          }
//      );
//    }
//    return sessionIntelligenceDtos;
//  }


  @GraphQLQuery(name = "sessionIntelligence")
  public List<SessionIntelligenceDto> getSessionIntelligenceBySessionId(
      @GraphQLArgument(name = "sessionIds") List<UUID> userIds
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
