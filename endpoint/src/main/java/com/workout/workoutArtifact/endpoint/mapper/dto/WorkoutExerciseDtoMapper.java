package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.endpoint.dto.WorkoutExerciseDto;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseDtoMapper {

  public WorkoutExerciseDto toDto(WorkoutExercise workoutExercise) {
    return new WorkoutExerciseDto(
        workoutExercise.getId(),
        workoutExercise.getSessionId(),
        workoutExercise.getExercise().getId(),
        workoutExercise.getExerciseNumber());
  }

}
