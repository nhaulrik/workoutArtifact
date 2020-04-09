package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseMuscleRelationEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {

  private final EntityManager entityManager;

  public ExerciseDto toDto(Exercise exercise) {
    return ExerciseDto.builder()
        .id(exercise.getId())
        .name(exercise.getName())
        .isCompound(exercise.getIsCompound())
        .bodyPart(exercise.getBodyPart())
        .build();
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
    return Exercise.builder()
        .id(exerciseEntity.getId())
        .name(exerciseEntity.getName())
        .isCompound(exerciseEntity.getIsCompound())
        .bodyPart(exerciseEntity.getBodyPart())
        .build();
  }

  public Exercise toDomainObject(ExerciseDto exerciseDto) {
    return Exercise.builder()
        .id(exerciseDto.getId())
        .name(exerciseDto.getName())
        .isCompound(exerciseDto.getIsCompound())
        .bodyPart(exerciseDto.getBodyPart())
        .build();
  }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName());
    exerciseEntity.setBodyPart(exercise.getBodyPart());
    exerciseEntity.setIsCompound(exercise.getIsCompound());
    exerciseEntity.setId(exercise.getId());
    return exerciseEntity;
  }

}
