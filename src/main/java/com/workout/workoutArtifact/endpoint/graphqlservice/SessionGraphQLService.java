package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDateTime;
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

  @GraphQLQuery(name = "getSessions")
  public List<SessionDto> getSessions(
      @GraphQLArgument(name = "ids") List<Long> ids,
      @GraphQLArgument(name = "locations") List<String> locations

  ) {
    List<AbstractSpecification<SessionDto>> sessionDtoSpecifications = new ArrayList<>();

    if (ids != null) { sessionDtoSpecifications.add(new SessionDto.IdsSpecification(ids)); }
    if (locations != null) { sessionDtoSpecifications.add(new SessionDto.LocationsSpecification(locations)); }

    AbstractSpecification aggregatedSpecification = sessionDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

  return sessionFacade.getSessions(aggregatedSpecification);
  }

  @GraphQLMutation(name = "addSession")
  public Boolean addSession(
      @GraphQLArgument(name = "location") String location,
      @GraphQLArgument(name = "time") String time,
      @GraphQLArgument(name = "workoutSetIds") List<Long> workoutSetIds
      ) {

    SessionDto sessionDto = SessionDto.builder()
        .location(location)
        .localDateTime(LocalDateTime.now()) // TODO: 18-09-2019 do some mapping from real argument here
        .workoutSetIds(workoutSetIds)
        .build();

    sessionFacade.addSessions(Arrays.asList(sessionDto));

    return true;
  }
}
