package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.graphql.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.graphql.dto.WorkoutSetDto;
import com.workout.workoutArtifact.graphql.fetcher.WorkoutSetFetcher;
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
public class WorkoutSetGraphQLService implements GraphQLService {

  private final WorkoutSetFetcher workoutSetFetcher;

//  @GraphQLMutation(name = "addWorkoutSetList")
//  public List<UUID> addWorkoutSetList(
//      @GraphQLArgument(name = "workoutSet") List<WorkoutSetDto> workoutSetDtos
//  ) {
//    List<UUID> workoutSetIds = new ArrayList<>();
//    workoutSetDtos.forEach(workoutSetDto -> {
//      UUID workoutSetId = workoutExerciseFetcher.addWorkoutSet(workoutSetDto);
//      workoutSetIds.add(workoutSetId);
//    });
//    return workoutSetIds;
//  }

//  @GraphQLMutation(name = "addWorkoutSet")
//  public UUID postWorkoutSet(
//      @GraphQLArgument(name = "id") UUID id,
//      @GraphQLArgument(name = "setNumber") Integer setNumber,
//      @GraphQLArgument(name = "weight") Double weight,
//      @GraphQLArgument(name = "repetitions") Integer repetitions,
//      @GraphQLArgument(name = "repetitionMaximum") Integer repetitionMaximum,
//      @GraphQLArgument(name = "single") Boolean single,
//      @GraphQLArgument(name = "workoutExerciseId") UUID workoutExerciseId,
//      @GraphQLArgument(name = "sessionId") UUID sessionId
//  ) {
//    return workoutExerciseFetcher.postWorkoutSet(
//        id,
//        setNumber,
//        weight,
//        repetitions,
//        repetitionMaximum,
//        single,
//        workoutExerciseId,
//        sessionId
//    );
//  }

//  @GraphQLMutation(name = "deleteWorkoutSet")
//  public Boolean deleteWorkoutSet(
//      @GraphQLArgument(name = "id") UUID id
//  ) {
//    workoutExerciseFetcher.deleteWorkoutSet(id);
//    return true;
//  }

  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "workoutExerciseIds") List<UUID> exerciseIds
  ) {

    List<AbstractSpecification<WorkoutSet>> workoutSetSpecifications = new ArrayList<>();
    if (ids != null) {workoutSetSpecifications.add(new WorkoutSet.IdsSpecification(ids)); }
    if (exerciseIds != null) {workoutSetSpecifications.add(new WorkoutSet.WorkoutExerciseIdsSpecification(ids)); }

    AbstractSpecification aggregatedSpecification = workoutSetSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return workoutSetFetcher.getWorkoutSet(aggregatedSpecification);
  }

  @GraphQLQuery(name = "workoutSet")
  public List<WorkoutSetDto> getWorkoutSet(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLContext WorkoutExerciseDto workoutExerciseDto
  ) {
    List<AbstractSpecification<WorkoutSet>> workoutSetSpecifications = new ArrayList<>();
    if (workoutExerciseDto != null) { workoutSetSpecifications.add(new WorkoutSet.WorkoutExerciseIdsSpecification(Arrays.asList(workoutExerciseDto.getId()))); }
    if (ids != null) { workoutSetSpecifications.add(new WorkoutSet.IdsSpecification(ids)); }

    AbstractSpecification aggregatedSpecification = workoutSetSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return workoutSetFetcher.getWorkoutSet(aggregatedSpecification);
  }

}
