package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import com.workout.workoutArtifact.mysql.repository.MuscleJpaRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ExerciseEntityMapper {

  private final MuscleJpaRepository muscleJpaRepository;

  public ExerciseEntity toEntity(Exercise exercise) {

    ExerciseEntity exerciseEntity = new ExerciseEntity(
        exercise.getId().toString(),
        exercise.getCreateDate(),
        LocalDateTime.now(),
        exercise.getName(),
        exercise.getIsCompound(),
        exercise.getBodyPart(),
        new ArrayList<>(),
        muscleJpaRepository.findAllByExerciseEntitiesId(exercise.getId().toString())
    );
    return exerciseEntity;
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
    Exercise exercise = Exercise.instantiate(
        exerciseEntity.getId(),
        exerciseEntity.getName(),
        exerciseEntity.getIsCompound(),
        exerciseEntity.getBodyPart(),
        exerciseEntity.getCreateDate(),
        exerciseEntity.getMuscleEntities().stream().map(MuscleEntity::getId).collect(Collectors.toList()));
    return exercise;
  }

}
