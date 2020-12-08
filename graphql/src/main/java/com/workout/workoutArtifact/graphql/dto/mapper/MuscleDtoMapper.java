package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MuscleDtoMapper {

  public MuscleDto toDto(MuscleEntity muscleEntity) {
    return new MuscleDto(
        muscleEntity.getId(),
        muscleEntity.getName(),
        muscleEntity.getBodyPart(),
        muscleEntity.getExerciseEntities().stream().map(ExerciseEntity::getId).collect(Collectors.toList())
    );
  }

}
