package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.endpoint.facade.WorkoutExerciseFacade;
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
public class WorkoutExerciseGraphQLService implements GraphQLService {

  private final WorkoutExerciseFacade workoutExerciseFacade;

  @GraphQLQuery(name = "workoutExercises")
  public List<WorkoutExerciseDto> getWorkoutExercise(
      @GraphQLArgument(name = "ids") List<String> ids,
      @GraphQLContext SessionDto sessionDto
  ) {

    List<AbstractSpecification<WorkoutExerciseDto>> workoutExerciseDtoSpecifications = new ArrayList<>();
    if (ids != null) {
      workoutExerciseDtoSpecifications.add(new WorkoutExerciseDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
    }

    AbstractSpecification aggregatedSpecification = workoutExerciseDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return workoutExerciseFacade.getWorkoutExercises(aggregatedSpecification);
  }

  @GraphQLMutation(name = "postWorkoutExercise")
  public UUID postWorkoutExercise(
      @GraphQLArgument(name = "id") UUID id,
      @GraphQLArgument(name = "exerciseNumber") Integer exerciseNumber,
      @GraphQLArgument(name = "sessionId") UUID sessionId
  ) {
    return workoutExerciseFacade.addWorkoutExercise(id, exerciseNumber, sessionId);
  }

}
