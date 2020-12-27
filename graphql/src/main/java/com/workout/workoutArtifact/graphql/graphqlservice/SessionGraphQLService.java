package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.graphql.fetcher.SessionFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
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

  private final SessionFetcher sessionFetcher;

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLContext UserDto userDto,
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "location") String location,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName
  ) {

    List<AbstractSpecification> abstractSpecifications = new ArrayList<>();

    abstractSpecifications.add(new Session.UserIdsSpecification(Arrays.asList(userDto.getId())));
    if (ids != null) { abstractSpecifications.add(new Session.IdsSpecification(ids)); }
    if (location != null) { abstractSpecifications.add(new Session.LocationsSpecification(Arrays.asList(location))); }
    if (programme != null) { abstractSpecifications.add(new Session.ProgrammeSpecification(programme)); }
    if (splitName != null) { abstractSpecifications.add(new Session.SplitNameSpecification(splitName)); }

    AbstractSpecification aggregatedSpecification = abstractSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());

    return sessionFetcher.getSessions(aggregatedSpecification);
  }

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "location") String location,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName,
      @GraphQLArgument(name = "date") String date,
      @GraphQLArgument(name = "month") Integer month,
      @GraphQLArgument(name = "year") Integer year
  ) {

    List<AbstractSpecification<Session>> sessionSpecifications = new ArrayList<>();
    AbstractSpecification aggregatedSpecification;
    if (ids != null) { sessionSpecifications.add(new Session.IdsSpecification(ids)); }
    if (location != null) { sessionSpecifications.add(new Session.LocationsSpecification(Arrays.asList(location))); }
    if (programme != null) { sessionSpecifications.add(new Session.ProgrammeSpecification(programme)); }
    if (splitName != null) { sessionSpecifications.add(new Session.SplitNameSpecification(splitName)); }
    if (date != null) {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

      LocalDateTime localDateTime = localDate.atStartOfDay();

      sessionSpecifications.add(new Session.DateTimeSpecification(localDateTime));
    }
    if (month != null && year != null) {
      YearMonth yearMonth = YearMonth.of(year, month);

      List<LocalDateTime> dateTimes = new ArrayList<>();

      for (int i = 1; i < yearMonth.lengthOfMonth(); i++) {
        LocalDate localDate = LocalDate.of(year, month, i);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        dateTimes.add(localDateTime);
        sessionSpecifications.add(new Session.DateTimeSpecification(localDateTime));
      }
      aggregatedSpecification = sessionSpecifications.stream().reduce(AbstractSpecification::or).orElse(new MatchAllSpecification());
      return sessionFetcher.getSessions(aggregatedSpecification);
    }

    aggregatedSpecification = sessionSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

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
