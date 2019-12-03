package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionFacade sessionFacade;

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLContext UserDto userDto
  ) {
    AbstractSpecification userIdSpecification = new SessionDto.UserIdSpecification(userDto.getId());
    return sessionFacade.getSessions(userIdSpecification);
  }

  @GraphQLQuery(name = "sessions")
  public List<SessionDto> getSessions(
      @GraphQLArgument(name = "ids") List<Long> ids,
      @GraphQLArgument(name = "locations") List<String> locations,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName
  ) {
    List<AbstractSpecification<SessionDto>> sessionDtoSpecifications = new ArrayList<>();

    if (ids != null) { sessionDtoSpecifications.add(new SessionDto.IdsSpecification(ids)); }
    if (locations != null) { sessionDtoSpecifications.add(new SessionDto.LocationsSpecification(locations)); }
    if (programme != null) { sessionDtoSpecifications.add(new SessionDto.ProgrammeSpecification(programme)); }
    if (splitName != null) { sessionDtoSpecifications.add(new SessionDto.SplitNameSpecification(splitName)); }

    AbstractSpecification aggregatedSpecification = sessionDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

  return sessionFacade.getSessions(aggregatedSpecification);
  }

  @GraphQLMutation(name = "addSession")
  public Boolean addSession(
      @GraphQLArgument(name = "location") String location,
      @GraphQLArgument(name = "programme") String programme,
      @GraphQLArgument(name = "splitName") String splitName,
      @GraphQLArgument(name = "time") String time,
      @GraphQLArgument(name = "workoutSetIds") List<Long> workoutSetIds,
      @GraphQLArgument(name = "userId") Long userId
      ) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime parsedTime = LocalDateTime.parse(time, dateTimeFormatter);

    SessionDto sessionDto = SessionDto.builder()
        .location(location)
        .programme(programme)
        .splitName(splitName)
        .localDateTime(parsedTime)
        .workoutSetIds(workoutSetIds != null ? workoutSetIds : new ArrayList<>())
        .userId(userId)
        .build();

    sessionFacade.addSessions(Arrays.asList(sessionDto));

    return true;
  }
}
