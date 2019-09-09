package com.workout.workoutArtifact.endpoint.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.NotSpecification;
import com.workout.workoutArtifact.specification.OrSpecification;
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
    }
    return new MatchAllSpecification();
  }
  
}
