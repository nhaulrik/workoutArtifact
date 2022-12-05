package com.workout.workoutArtifact.intelligence.session;

import com.workout.workoutArtifact.application.intelligence.dto.SessionIntelligenceDto;
import com.workout.workoutArtifact.application.intelligence.dto.SessionWorkoutExerciseDto;
import com.workout.workoutArtifact.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
public class SessionIntelligenceGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionService sessionService;

  @GraphQLQuery(name = "sessionIntelligence")
  public List<SessionIntelligenceDto> getSessionIntelligence(
      @GraphQLArgument(name = "userId") UUID userId,
      @GraphQLArgument(name = "fromDateString") String fromDateString,
      @GraphQLArgument(name = "toDateString") String toDateString
  ) {

    List<AbstractSpecification<Session>> sessionSpecification = new ArrayList<>();

    if (userId != null) { sessionSpecification.add(new Session.UserIdsSpecification(Arrays.asList(userId)));}
    if (fromDateString != null) {
      LocalDateTime fromDate = getTime(fromDateString);
      LocalDateTime toDate = toDateString != null ? getTime(toDateString) : LocalDateTime.now();

      sessionSpecification.add(new Session.BetweenDateTimeSpecification(fromDate, toDate));
    }

    AbstractSpecification aggregatedSpecification = sessionSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    List<Session> sessions = sessionService.getSessions(aggregatedSpecification);

    List<SessionIntelligenceDto> resultList = new ArrayList<>();
    sessions.forEach(session -> {
      resultList.add(new SessionIntelligenceDto(
          session.getUserId().toString(),
          session.getTotalWeight(),
          session.getTotalRepetitions(),
          session.getCreationDateTime().toLocalDate(),
          session.getCalories(),
          session.getDuration() != null ? session.getDuration().toMinutes() : null,
          SessionWorkoutExerciseDto.fromWorkoutExercises(session.getWorkoutExercises())
      ));
    });

    return resultList.stream()
        .sorted(Comparator.comparing(SessionIntelligenceDto::getDate).reversed())
        .collect(Collectors.toList());
  }

  private LocalDateTime getTime(String value) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate parsedDate = LocalDate.parse(value, dateTimeFormatter);
    return LocalDateTime.of(parsedDate, LocalTime.MIN);
  }

}
