package com.workout.workoutArtifact.endpoint.mapper.specification;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.NotSpecification;
import com.workout.workoutArtifact.domain.specification.OrSpecification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.endpoint.dto.WorkoutExerciseDto;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseDtoSpecificationMapper {

  public AbstractSpecification<WorkoutExercise> toWorkoutExerciseSpecification(AbstractSpecification<WorkoutExerciseDto> WorkoutExerciseDtoSpecification) {

    if (WorkoutExerciseDtoSpecification instanceof AndSpecification) {
      return ((AndSpecification<WorkoutExerciseDto>) WorkoutExerciseDtoSpecification).getSet().stream().map(this::toWorkoutExerciseSpecification).reduce(AbstractSpecification::and).orElseThrow(() -> new RuntimeException("Missing specifications"));
    } else if (WorkoutExerciseDtoSpecification instanceof OrSpecification) {
      return ((OrSpecification<WorkoutExerciseDto>) WorkoutExerciseDtoSpecification).getSet().stream().map(this::toWorkoutExerciseSpecification).reduce(AbstractSpecification::or).orElseThrow(() -> new RuntimeException("Missing specifications"));
    } else if (WorkoutExerciseDtoSpecification instanceof NotSpecification) {
      return new NotSpecification<>(toWorkoutExerciseSpecification(((NotSpecification<WorkoutExerciseDto>) WorkoutExerciseDtoSpecification).getSpecification()));
    } else if (WorkoutExerciseDtoSpecification instanceof WorkoutExerciseDto.IdsSpecification) {
      return new WorkoutExercise.IdsSpecification(((WorkoutExerciseDto.IdsSpecification) WorkoutExerciseDtoSpecification).getIds());
    } else if (WorkoutExerciseDtoSpecification instanceof WorkoutExerciseDto.SessionIdsSpecification) {
      return new WorkoutExercise.SessionIdsSpecification(((WorkoutExerciseDto.SessionIdsSpecification) WorkoutExerciseDtoSpecification).getSessionIds());
    }
    return new MatchAllSpecification();
  }

}
