package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkoutSetGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final WorkoutSetFacade workoutSetFacade;

  @GraphQLMutation(name = "addWorkoutSetList")
  public List<Long> addWorkoutSetList(
      @GraphQLArgument(name = "workoutSet") List<WorkoutSetDto> workoutSetDtos
  ) {
    List<Long> workoutSetIds = new ArrayList<>();
    workoutSetDtos.forEach(workoutSetDto -> {
      Long workoutSetId = workoutSetFacade.addWorkoutSet(workoutSetDto);
      workoutSetIds.add(workoutSetId);
    });
    return workoutSetIds;
  }

  @GraphQLMutation(name = "addWorkoutSet")
  public Boolean addWorkoutSet(
      @GraphQLArgument(name = "id") Long id,
      @GraphQLArgument(name = "setNumber") Integer setNumber,
      @GraphQLArgument(name = "weight") Double weight,
      @GraphQLArgument(name = "repetitions") Integer repetitions,
      @GraphQLArgument(name = "repetitionMaximum") Integer repetitionMaximum,
      @GraphQLArgument(name = "single") Boolean single,
      @GraphQLArgument(name = "exerciseId") Long exerciseId,
      @GraphQLArgument(name = "sessionId") UUID sessionId
  ) {

    WorkoutSetDto workoutSetDto = new WorkoutSetDto(
        null,
        sessionId,
        exerciseId,
        repetitions,
        weight,
        single,
        repetitionMaximum,
        setNumber
    );

    if (id != null) {
      workoutSetDto.setId(id);
    }

    workoutSetFacade.addWorkoutSet(workoutSetDto);
    return true;
  }

  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<Long> ids
  ) {

    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
    if (ids != null) {
      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids));
    }

    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
  }


  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<Long> ids,
      @GraphQLContext SessionDto sessionDto
  ) {
    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
    if (sessionDto != null && !sessionDto.getWorkoutSetIds().isEmpty()) {
      workoutSetDtoSpecifications.add(new IdsSpecification(sessionDto.getWorkoutSetIds()));
    }
    if (ids != null) {
      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids));
    }

    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
  }

}
