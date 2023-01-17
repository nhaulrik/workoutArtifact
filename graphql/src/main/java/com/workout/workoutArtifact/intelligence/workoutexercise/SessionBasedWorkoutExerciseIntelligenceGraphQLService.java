package com.workout.workoutArtifact.intelligence.workoutexercise;

import com.workout.workoutArtifact.application.intelligence.ExerciseBasedWorkoutExerciseIntelligence;
import com.workout.workoutArtifact.application.intelligence.SessionBasedWorkoutExerciseIntelligence;
import com.workout.workoutArtifact.application.intelligence.dto.SessionBasedWorkoutExerciseIntelligenceDto;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionBasedWorkoutExerciseIntelligenceGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionBasedWorkoutExerciseIntelligence sessionBasedWorkoutExerciseIntelligence;

  private final static Integer DEFAULT_SESSION_AMOUNT = 10;

  @GraphQLQuery(name = "sessionBasedExerciseIntelligence")
  public List<SessionBasedWorkoutExerciseIntelligenceDto> getExerciseIntelligence(
      @GraphQLArgument(name = "userId") UUID userId,
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
    sessionsBack = sessionsBack != null ? sessionsBack : DEFAULT_SESSION_AMOUNT;


    specifications.add(new Session.UserIdsSpecification(Arrays.asList(userId)));

    AbstractSpecification aggregatedSpecification = specifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());

    return sessionBasedWorkoutExerciseIntelligence.getSessionBasedIntelligence(aggregatedSpecification, userId, sessionsBack);
  }

  private LocalDateTime getTime(String value) {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate parsedDate = LocalDate.parse(value, dateTimeFormatter);
      return LocalDateTime.of(parsedDate, LocalTime.MIN);
  }

}
