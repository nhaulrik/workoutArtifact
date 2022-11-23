package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.WorkoutSetDto;
import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WorkoutSetDtoMapper {

  public WorkoutSetDto toDto(WorkoutSetEntity workoutSetEntity) {
    return new WorkoutSetDto(
        workoutSetEntity.getId(),
        workoutSetEntity.getRepetitions(),
        workoutSetEntity.getWeight(),
        workoutSetEntity.getSingle(),
        workoutSetEntity.getRepetitionMaximum(),
        workoutSetEntity.getSetNumber(),
        workoutSetEntity.getWorkoutExerciseEntity().getId()
    );
  }

}
