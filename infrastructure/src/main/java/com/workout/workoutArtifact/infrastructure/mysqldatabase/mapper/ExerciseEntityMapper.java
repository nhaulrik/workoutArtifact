package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ExerciseEntityMapper {

  private final MuscleEntityMapper muscleEntityMapper;

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName());
    exerciseEntity.setBodyPart(exercise.getBodyPart());
    exerciseEntity.setIsCompound(exercise.getIsCompound());
    exerciseEntity.setId(exercise.getId().toString());
    return exerciseEntity;
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
    Exercise exercise = Exercise.instantiate(
        UUID.fromString(exerciseEntity.getId()),
        exerciseEntity.getName(),
        exerciseEntity.getIsCompound(),
        exerciseEntity.getBodyPart(),
        exerciseEntity.getCreateDate(),
        exerciseEntity.getMuscleEntities().stream().map(muscleEntityMapper::toDomainObject).collect(Collectors.toList()));
    return exercise;
  }

}
