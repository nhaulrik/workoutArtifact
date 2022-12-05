package com.workout.workoutArtifact.intelligence;

import com.workout.workoutArtifact.application.intelligence.ExerciseIntelligence;
import com.workout.workoutArtifact.application.intelligence.SessionIntelligence;
import com.workout.workoutArtifact.application.intelligence.WorkoutExerciseIntelligence;
import com.workout.workoutArtifact.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntelligenceGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final WorkoutExerciseIntelligence workoutExerciseIntelligence;
  private final SessionIntelligence sessionIntelligence;

  @GraphQLQuery(name = "exerciseIntelligence")
  public ExerciseIntelligence getExerciseIntelligence(
      @GraphQLArgument(name = "userId") UUID userId,
      @GraphQLArgument(name = "exerciseIds") List<UUID> exerciseIds,
      @GraphQLArgument(name = "fromDateString") String fromDateString,
      @GraphQLArgument(name = "toDateString") String toDateString,
      @GraphQLArgument(name = "sessionsBack") Integer sessionsBack
  ) {

    List<AbstractSpecification> specifications = new ArrayList<>();

    if (fromDateString != null && toDateString != null) {
      LocalDateTime fromDate = getTime(fromDateString);
      LocalDateTime toDate = getTime(toDateString);

      specifications.add(new Session.BetweenDateTimeSpecification(fromDate, toDate));
    }

    specifications.add(new Session.UserIdsSpecification(Arrays.asList(userId)));

    AbstractSpecification aggregatedSpecification = specifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());

    return workoutExerciseIntelligence.getIntelligence(aggregatedSpecification, userId, sessionsBack, exerciseIds);
  }

  private LocalDateTime getTime(String value) {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate parsedDate = LocalDate.parse(value, dateTimeFormatter);
      return LocalDateTime.of(parsedDate, LocalTime.MIN);
  }
//
//  @GraphQLQuery(name = "sessionIntelligence")
//  public List<SessionIntelligence.SessionIntelligenceDto> getSessionIntelligence(
//      @GraphQLArgument(name = "userId") @NonNull UUID userId,
//      @GraphQLArgument(name = "sessionsBack") @NonNull Integer sessionsBack
//  ) {
//    return sessionIntelligence.getSessionIntelligence(userId, sessionsBack);
//  }

}
