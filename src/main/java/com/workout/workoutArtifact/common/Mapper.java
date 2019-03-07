package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;

public class Mapper {

  public static Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = new Muscle();
    muscle.setName(muscleEntity.getName());
    return muscle;
  }

}
