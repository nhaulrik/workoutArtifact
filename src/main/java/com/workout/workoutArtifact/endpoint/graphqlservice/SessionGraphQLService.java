package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final SessionFacade sessionFacade;

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
}
