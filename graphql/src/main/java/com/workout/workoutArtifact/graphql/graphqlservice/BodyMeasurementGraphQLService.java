package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.domain.bodymeasurement.model.BodyMeasurement;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.graphql.dto.BodyMeasurementDto;
import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.graphql.fetcher.BodyMeasurementFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
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
public class BodyMeasurementGraphQLService implements GraphQLService {

  private final BodyMeasurementFetcher bodyMeasurementFetcher;

  @GraphQLQuery(name = "bodyMeasurements")
  public List<BodyMeasurementDto> getBodyMeasurements(
      @GraphQLArgument(name = "userId") UUID userId
  ) {
    List<AbstractSpecification<BodyMeasurement>> bodyMeasurementSpecifications = new ArrayList<>();

    if (userId != null) {
      bodyMeasurementSpecifications.add(new BodyMeasurement.UserIdsSpecification(Arrays.asList(userId)));
    }
    AbstractSpecification aggregatedSpecification = bodyMeasurementSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return bodyMeasurementFetcher.getBodyMeasurements(aggregatedSpecification);
  }


  @GraphQLQuery(name = "bodyMeasurements")
  public List<BodyMeasurementDto> getBodyMeasurements(
      @GraphQLContext UserDto userDto
  ) {
    List<AbstractSpecification<BodyMeasurement>> bodyMeasurementSpecifications = new ArrayList<>();

    if (userDto != null) {
      bodyMeasurementSpecifications.add(new BodyMeasurement.UserIdsSpecification(Arrays.asList(userDto.getId())));
    }
    AbstractSpecification aggregatedSpecification = bodyMeasurementSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return bodyMeasurementFetcher.getBodyMeasurements(aggregatedSpecification);
  }


}
