package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import java.util.stream.Collectors;

public class Mapper {

  public static Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = new Muscle();
    muscle.setMuscle(MuscleEnum.valueOf(muscleEntity.getName()));
    muscle.setBodyPart(BodyPartEnum.valueOf(muscleEntity.getBodyPart()));
    return muscle;
  }

  public static com.workout.workoutArtifact.endpoint.domain.Exercise toDomainObject(
      ExerciseEntity exerciseEntity) {
    com.workout.workoutArtifact.endpoint.domain.Exercise exercise = new com.workout.workoutArtifact.endpoint.domain.Exercise();
    exercise.setName(ExerciseEnum.valueOf(exerciseEntity.getName()));
    exercise.setIsMultiJoint(exerciseEntity.getIsMultiJoint());
    exercise.setMuscles(exerciseEntity.getMuscleEntities().stream()
        .map(entity -> toDomainObject(entity))
        .collect(Collectors.toList()));
    return exercise;
  }

  public static MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.toString());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

  public static ExerciseEntity toEntity(
      com.workout.workoutArtifact.endpoint.domain.Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName().toString());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    return exerciseEntity;
  }

}
