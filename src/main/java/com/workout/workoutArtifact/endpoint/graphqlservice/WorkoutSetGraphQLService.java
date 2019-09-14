package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkoutSetGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final WorkoutSetFacade workoutSetFacade;

  @GraphQLMutation(name = "addWorkoutSet")
  public String addWorkoutSet(
      @GraphQLArgument(name= "setNumber") Integer setNumber,
      @GraphQLArgument(name= "weight") Double weight,
      @GraphQLArgument(name= "repetitions") Integer repetitions,
      @GraphQLArgument(name= "repetitionMaximum") Integer repetitionMaximum,
      @GraphQLArgument(name= "single") Boolean single,
      @GraphQLArgument(name= "exerciseId") Long exerciseId
  ) {

    WorkoutSetDto workoutSetDto = WorkoutSetDto.builder()
        .setNumber(setNumber)
        .weight(weight)
        .repetitions(repetitions)
        .repetitionMaximum(repetitionMaximum)
        .single(single)
        .exerciseId(exerciseId)
        .build();

    workoutSetFacade.addWorkoutSet(workoutSetDto);
    return "WorkoutSet added";
  }

  @GraphQLQuery(name = "workoutsets")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<Long> ids
  ) {

    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
    if (ids != null) { workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids)); }

    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
  }

}
