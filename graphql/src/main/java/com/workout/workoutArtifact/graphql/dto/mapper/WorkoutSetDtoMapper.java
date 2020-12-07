package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.WorkoutSetDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
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
