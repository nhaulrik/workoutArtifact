package com.workout.workoutArtifact.graphqlservice;

import com.workout.workoutArtifact.dto.SessionDto;
import com.workout.workoutArtifact.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.fetcher.WorkoutExerciseFetcher;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.configuration.GraphQLSPQRConfig.GraphQLService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkoutExerciseGraphQLService implements GraphQLService {

  private final WorkoutExerciseFetcher workoutExerciseFetcher;

  @GraphQLQuery(name = "workoutExercises")
  public List<WorkoutExerciseDto> getWorkoutExercise(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "sessionIds") List<UUID> sessionIds,
      @GraphQLArgument(name = "exerciseNumbers") List<Integer> exerciseNumbers
  ) {

    List<AbstractSpecification<WorkoutExercise>> workoutExerciseSpecifications = new ArrayList<>();
    if (ids != null) { workoutExerciseSpecifications.add(new WorkoutExercise.IdsSpecification(ids)); }
    if (sessionIds != null) { workoutExerciseSpecifications.add(new WorkoutExercise.SessionIdsSpecification(sessionIds)); }
    if (exerciseNumbers != null) { workoutExerciseSpecifications.add(new WorkoutExercise.ExerciseNumbersSpecification(exerciseNumbers)); }

    AbstractSpecification aggregatedSpecification = workoutExerciseSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification<>());

    return workoutExerciseFetcher.getWorkoutExercises(aggregatedSpecification);
  }


  @GraphQLQuery(name = "workoutExercises")
  public List<WorkoutExerciseDto> getWorkoutExercise(
      @GraphQLArgument(name = "ids") List<String> ids,
      @GraphQLArgument(name = "sessionIds") List<UUID> sessionIds,
      @GraphQLArgument(name = "exerciseNumbers") List<Integer> exerciseNumbers,
      @GraphQLContext SessionDto sessionDto
  ) {

    List<AbstractSpecification<WorkoutExercise>> workoutExerciseSpecifications = new ArrayList<>();
    if (ids != null) { workoutExerciseSpecifications.add(new WorkoutExercise.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList()))); }
    if (sessionIds != null) { workoutExerciseSpecifications.add(new WorkoutExercise.SessionIdsSpecification(sessionIds)); }
    if (exerciseNumbers != null) { workoutExerciseSpecifications.add(new WorkoutExercise.ExerciseNumbersSpecification(exerciseNumbers)); }
    if (sessionDto != null) { workoutExerciseSpecifications.add((new WorkoutExercise.SessionIdsSpecification(Arrays.asList(sessionDto.getId())))); }

    AbstractSpecification aggregatedSpecification = workoutExerciseSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());

    return workoutExerciseFetcher.getWorkoutExercises(aggregatedSpecification);
  }

//  @GraphQLMutation(name = "postWorkoutExercise")
//  public UUID postWorkoutExercise(
//      @GraphQLArgument(name = "id") UUID id,
//      @GraphQLArgument(name = "exerciseNumber") Integer exerciseNumber,
//      @GraphQLArgument(name = "sessionId") UUID sessionId,
//      @GraphQLArgument(name = "exerciseId") UUID exerciseId
//  ) {
//    return workoutExerciseFetcher.postWorkoutExercise(id, exerciseNumber, sessionId, exerciseId);
//  }

//  @GraphQLMutation(name = "deleteWorkoutExercise")
//  public Boolean deleteWorkoutExercise(
//      @GraphQLArgument(name = "id") UUID id
//  ) {
//    return workoutExerciseFetcher.deleteWorkoutExercise(id);
//  }

}
