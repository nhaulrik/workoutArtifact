package com.workout.workoutArtifact.endpoint.mapper.specification;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.NotSpecification;
import com.workout.workoutArtifact.domain.specification.OrSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetDtoSpecificationMapper {

  public AbstractSpecification<WorkoutSet> toWorkoutSetSpecification(AbstractSpecification<WorkoutSetDto> workoutSetDtoSpecification) {

    if (workoutSetDtoSpecification instanceof AndSpecification) {
      return ((AndSpecification<WorkoutSetDto>) workoutSetDtoSpecification).getSet().stream().map(this::toWorkoutSetSpecification).reduce(AbstractSpecification::and).orElseThrow(() -> new RuntimeException("Missing specifications"));
    } else if (workoutSetDtoSpecification instanceof OrSpecification) {
      return ((OrSpecification<WorkoutSetDto>) workoutSetDtoSpecification).getSet().stream().map(this::toWorkoutSetSpecification).reduce(AbstractSpecification::or).orElseThrow(() -> new RuntimeException("Missing specifications"));
    } else if (workoutSetDtoSpecification instanceof NotSpecification) {
      return new NotSpecification<>(toWorkoutSetSpecification(((NotSpecification<WorkoutSetDto>) workoutSetDtoSpecification).getSpecification()));
    } else if (workoutSetDtoSpecification instanceof WorkoutSetDto.IdsSpecification) {
      return new WorkoutSet.IdsSpecification(((WorkoutSetDto.IdsSpecification) workoutSetDtoSpecification).getIds());
    } else if (workoutSetDtoSpecification instanceof WorkoutSetDto.WorkoutExerciseIdsSpecification) {
      return new WorkoutSet.WorkoutExerciseIdsSpecification(((WorkoutSetDto.WorkoutExerciseIdsSpecification) workoutSetDtoSpecification).getIds());
    }
    return new MatchAllSpecification();
  }

}
