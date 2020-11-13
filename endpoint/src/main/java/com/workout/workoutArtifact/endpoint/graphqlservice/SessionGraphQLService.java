package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
public class SessionGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionFacade sessionFacade;

  @GraphQLQuery(name = "deleteSessions")
  public Boolean deleteSessions(
      @GraphQLArgument(name = "ids") List<UUID> ids
  ) {
    AbstractSpecification sessionIdSpecification = new SessionDto.IdsSpecification(ids);
    return sessionFacade.deleteSessions(sessionIdSpecification);
  }

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLContext UserDto userDto
  ) {
    AbstractSpecification userIdSpecification = new SessionDto.UserIdSpecification(userDto.getId());
    return sessionFacade.getSessions(userIdSpecification);
  }

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "locations") List<String> locations,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName,
      @GraphQLArgument(name = "userId") UUID userId,
      @GraphQLArgument(name = "date") String date,
      @GraphQLArgument(name = "month") Integer month,
      @GraphQLArgument(name = "year") Integer year

  ) {
    List<AbstractSpecification<SessionDto>> sessionDtoSpecifications = new ArrayList<>();
    AbstractSpecification aggregatedSpecification;
    if (ids != null) {
      sessionDtoSpecifications.add(new SessionDto.IdsSpecification(ids));
    }
    if (locations != null) {
      sessionDtoSpecifications.add(new SessionDto.LocationsSpecification(locations));
    }
    if (programme != null) {
      sessionDtoSpecifications.add(new SessionDto.ProgrammeSpecification(programme));
    }
    if (splitName != null) {
      sessionDtoSpecifications.add(new SessionDto.SplitNameSpecification(splitName));
    }
    if (userId != null) {
      sessionDtoSpecifications.add(new SessionDto.UserIdSpecification(userId));
    }
    if (date != null) {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

      LocalDateTime localDateTime = localDate.atStartOfDay();

      sessionDtoSpecifications.add(new SessionDto.DateTimeSpecification(localDateTime));
    }
    if (month != null && year != null) {
      YearMonth yearMonth = YearMonth.of(year, month);

      List<LocalDateTime> dateTimes = new ArrayList<>();

      for (int i = 1; i < yearMonth.lengthOfMonth(); i++) {
        LocalDate localDate = LocalDate.of(year, month, i);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        dateTimes.add(localDateTime);
        sessionDtoSpecifications.add(new SessionDto.DateTimeSpecification(localDateTime));
      }
      aggregatedSpecification = sessionDtoSpecifications.stream().reduce(AbstractSpecification::or).orElse(new MatchAllSpecification());
      return sessionFacade.getSessions(aggregatedSpecification);
    }

    aggregatedSpecification = sessionDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    List<SessionDto> sessionDtos = new ArrayList<>();
    sessionDtos.addAll(sessionFacade.getSessions(aggregatedSpecification));
    return sessionDtos;
  }

  @GraphQLMutation(name = "addSession")
  public UUID addSession(
      @GraphQLArgument(name = "id") UUID id,
      @GraphQLArgument(name = "location") String location,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName,
      @GraphQLArgument(name = "time") String time,
      @GraphQLArgument(name = "workoutSetIds") List<WorkoutSetDto> workoutSetDtos,
      @GraphQLArgument(name = "userId") UserDto user
  ) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime parsedTime = LocalDateTime.parse(time, dateTimeFormatter);

    SessionDto sessionDto = SessionDto.builder()
        .location(location)
        .programme(programme)
        .splitName(splitName)
        .localDateTime(parsedTime)
        .workoutSetDtos(workoutSetDtos != null ? workoutSetDtos : new ArrayList<>())
        .userDto(user)
        .build();

    if (id != null) {
      sessionDto.setId(id);
    }

    UUID sessionId = sessionFacade.addSessions(Arrays.asList(sessionDto)).get(0);

    return sessionId;
  }
}
