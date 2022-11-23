package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.ExerciseDto;
import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ExerciseDtoMapper {

  public ExerciseDto toDto(ExerciseEntity exerciseEntity) {
    return new ExerciseDto(
        exerciseEntity.getId(),
        exerciseEntity.getName(),
        exerciseEntity.getBodyPart(),
        exerciseEntity.getIsCompound(),
        exerciseEntity.getMuscleEntities().stream().map(MuscleEntity::getId).collect(Collectors.toList())
    );
  }
}
