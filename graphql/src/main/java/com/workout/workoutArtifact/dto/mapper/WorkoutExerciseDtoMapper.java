package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WorkoutExerciseDtoMapper {

  public WorkoutExerciseDto toDto(WorkoutExerciseEntity workoutExerciseEntity) {
    return new WorkoutExerciseDto(
        workoutExerciseEntity.getId(),
        workoutExerciseEntity.getSessionEntity().getId(),
        workoutExerciseEntity.getExerciseEntity().getId(),
        workoutExerciseEntity.getExerciseNumber(),
        workoutExerciseEntity.getIsWarmup());
  }

}
