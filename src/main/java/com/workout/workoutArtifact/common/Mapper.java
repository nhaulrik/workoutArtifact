package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import java.util.stream.Collectors;

public class Mapper {

  public static Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = new Muscle(
        MuscleEnum.valueOf(muscleEntity.getName()),
        BodyPartEnum.valueOf(muscleEntity.getBodyPart())
    );

    muscle.setExerciseList(muscleEntity.getExerciseSet().stream()
        .map(entity -> new Exercise(
                ExerciseEnum.valueOf(entity.getName()),
                entity.getIsMultiJoint()
            )
        ).collect(Collectors.toList()));
    return muscle;
  }

  public static com.workout.workoutArtifact.endpoint.domain.Exercise toDomainObject(
      ExerciseEntity exerciseEntity) {

    Exercise exercise = new Exercise(
        ExerciseEnum.valueOf(exerciseEntity.getName()),
        exerciseEntity.getIsMultiJoint()
    );

    exercise.setMuscles(exerciseEntity.getMuscleEntities().stream()
        .map(entity -> new Muscle(
            MuscleEnum.valueOf(entity.getName()),
            BodyPartEnum.valueOf(entity.getBodyPart()))
        )
        .collect(Collectors.toList()));
    return exercise;
  }

  public static MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getMuscle().toString());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

  public static ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName().toString());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    return exerciseEntity;
  }

}
