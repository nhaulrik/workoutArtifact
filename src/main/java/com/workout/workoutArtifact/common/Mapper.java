package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;

public class Mapper {

  public static Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = new Muscle();
    muscle.setName(muscleEntity.getName());
    muscle.setIsUpperBody(muscleEntity.getIsUpperBody());
    return muscle;
  }

  public static MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getName());
    muscleEntity.setIsUpperBody(muscle.getIsUpperBody());
    return muscleEntity;
  }

}
