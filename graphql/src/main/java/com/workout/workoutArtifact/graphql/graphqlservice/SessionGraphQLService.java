package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.graphql.model.SessionFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import specification.AbstractSpecification;
import specification.MatchAllSpecification;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionFetcher sessionFetcher;

//  @GraphQLQuery(name = "deleteSessions")
//  public Boolean deleteSessions(
//      @GraphQLArgument(name = "ids") List<UUID> ids
//  ) {
//    AbstractSpecification sessionIdSpecification = new IdsSpecification(ids);
//    return sessionFetcher.deleteSessions(sessionIdSpecification);
//  }

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLContext UserDto userDto
  ) {
    AbstractSpecification userIdSpecification = new SessionDto.UserIdSpecification(userDto.getId());
    return sessionFetcher.getSessions(userIdSpecification);
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
      return sessionFetcher.getSessions(aggregatedSpecification);
    }

    aggregatedSpecification = sessionDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    List<SessionDto> sessionDtos = new ArrayList<>();
    sessionDtos.addAll(sessionFetcher.getSessions(aggregatedSpecification));
    return sessionDtos;
  }


//  @GraphQLMutation(name = "postSession")
//  public Boolean postSession(
//      @GraphQLArgument(name = "id") UUID id,
//      @GraphQLArgument(name = "location") String location,
//      @GraphQLArgument(name = "programme") String programme,
//      @GraphQLArgument(name = "splitName") String splitName,
//      @GraphQLArgument(name = "time") String time,
//      @GraphQLArgument(name = "workoutSetIds") List<String> workoutSetIds,
//      @GraphQLArgument(name = "userId") UUID userId
//  ) {
//
//    if (id != null) {
//      return sessionFetcher.postSession(
//          id,
//          location,
//          programme,
//          splitName,
//          time,
//          userId
//      );
//    } else {
//      log.error("PostSession request contained no sessionId");
//      return false;
//    }
//  }


//  @GraphQLMutation(name = "addSession")
//  public UUID addSession(
//      @GraphQLArgument(name = "id") UUID id,
//      @GraphQLArgument(name = "location") String location,
//      @GraphQLArgument(name = "programme") String programme,
//      @GraphQLArgument(name = "splitName") String splitName,
//      @GraphQLArgument(name = "time") String time,
//      @GraphQLArgument(name = "workoutSetIds") List<String> workoutSetIds,
//      @GraphQLArgument(name = "userId") UUID userId
//  ) {
//
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//    LocalDateTime parsedTime = LocalDateTime.parse(time, dateTimeFormatter);
//
//    SessionDto sessionDto = SessionDto.builder()
//        .location(location)
//        .programme(programme)
//        .splitName(splitName)
//        .localDateTime(parsedTime)
//        .workoutSetIds(workoutSetIds != null ? workoutSetIds.stream().map(UUID::fromString).collect(Collectors.toList()) : new ArrayList<>())
//        .userId(userId)
//        .build();
//
//    if (id != null) {
//      sessionDto.setId(id);
//    }
//
//    UUID sessionId = sessionFetcher.addSessions(Arrays.asList(sessionDto)).get(0);
//
//    return sessionId;
//  }

//  @GraphQLMutation(name = "deleteSession")
//  public Boolean deleteSession(
//      @GraphQLArgument(name = "id") UUID id
//  ) {
//    Assert.notNull(id, "id is required");
//    return this.sessionFetcher.deleteSessions(new IdsSpecification(Arrays.asList(id)));
//  }
}
