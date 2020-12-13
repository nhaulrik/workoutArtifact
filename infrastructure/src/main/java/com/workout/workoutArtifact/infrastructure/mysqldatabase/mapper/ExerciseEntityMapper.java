package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ExerciseEntityMapper {

  private final MuscleEntityMapper muscleEntityMapper;
  private final EntityManager entityManager;

  public ExerciseEntity toEntity(Exercise exercise) {

//    List<MuscleEntity> muscleEntities = new ArrayList<>();
//
//    exercise.getMuscles().forEach(muscleEntity -> {
//      muscleEntities.add(entityManager.getReference(MuscleEntity.class, muscleEntity.getId()));
//    });

    ExerciseEntity exerciseEntity = new ExerciseEntity(
        exercise.getId().toString(),
        exercise.getCreateDate(),
        LocalDateTime.now(),
        exercise.getName(),
        exercise.getIsCompound(),
        exercise.getBodyPart(),
        new ArrayList<>(),
        exercise.getMuscles().stream().map(muscleEntityMapper::toEntity).collect(Collectors.toList())
    );

    exerciseEntity.getMuscleEntities().forEach(muscleEntity -> muscleEntity.setExerciseEntities(Arrays.asList(exerciseEntity)));

    return exerciseEntity;
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
    Exercise exercise = Exercise.instantiate(
        exerciseEntity.getId(),
        exerciseEntity.getName(),
        exerciseEntity.getIsCompound(),
        exerciseEntity.getBodyPart(),
        exerciseEntity.getCreateDate(),
        exerciseEntity.getMuscleEntities().stream().map(muscleEntityMapper::toDomainObject).collect(Collectors.toList()));
    return exercise;
  }

}
