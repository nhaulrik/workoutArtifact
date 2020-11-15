package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkoutSetGraphQLService implements GraphQLService {

  private final WorkoutSetFacade workoutSetFacade;

  @GraphQLMutation(name = "addWorkoutSetList")
  public List<UUID> addWorkoutSetList(
      @GraphQLArgument(name = "workoutSet") List<WorkoutSetDto> workoutSetDtos
  ) {
    List<UUID> workoutSetIds = new ArrayList<>();
    workoutSetDtos.forEach(workoutSetDto -> {
      UUID workoutSetId = workoutSetFacade.addWorkoutSet(workoutSetDto);
      workoutSetIds.add(workoutSetId);
    });
    return workoutSetIds;
  }

  @GraphQLMutation(name = "addWorkoutSet")
  public Boolean addWorkoutSet(
      @GraphQLArgument(name = "id") String id,
      @GraphQLArgument(name = "setNumber") Integer setNumber,
      @GraphQLArgument(name = "weight") Double weight,
      @GraphQLArgument(name = "repetitions") Integer repetitions,
      @GraphQLArgument(name = "repetitionMaximum") Integer repetitionMaximum,
      @GraphQLArgument(name = "single") Boolean single,
      @GraphQLArgument(name = "exerciseId") String exerciseId,
      @GraphQLArgument(name = "sessionId") UUID sessionId
  ) {

    WorkoutSetDto workoutSetDto = new WorkoutSetDto(
        null,
        sessionId,
        UUID.fromString(exerciseId),
        repetitions,
        weight,
        single,
        repetitionMaximum,
        setNumber
    );

    if (id != null) {
      workoutSetDto.setId(UUID.fromString(id));
    }

    workoutSetFacade.addWorkoutSet(workoutSetDto);
    return true;
  }

  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<String> ids
  ) {

    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
    if (ids != null) {
      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
    }

    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
  }


  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<String> ids,
      @GraphQLContext SessionDto sessionDto
  ) {
    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
    if (sessionDto != null && !sessionDto.getWorkoutSetIds().isEmpty()) {
      workoutSetDtoSpecifications.add(new IdsSpecification(sessionDto.getWorkoutSetIds()));
    }
    if (ids != null) {
      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
    }

    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
  }

}
