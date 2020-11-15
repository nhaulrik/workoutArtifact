package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.workout.workoutArtifact.domain.exercise.model.Exercise;

@Component
@RequiredArgsConstructor
public class ExerciseEntityMapper {

  private final EntityManager entityManager;


  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName());
    exerciseEntity.setBodyPart(exercise.getBodyPart());
    exerciseEntity.setIsCompound(exercise.getIsCompound());
    exerciseEntity.setId(exercise.getId().toString());
    return exerciseEntity;
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
    return Exercise.builder()
        .id(UUID.fromString(exerciseEntity.getId()))
        .name(exerciseEntity.getName())
        .isCompound(exerciseEntity.getIsCompound())
        .bodyPart(exerciseEntity.getBodyPart())
        .build();
  }

}
