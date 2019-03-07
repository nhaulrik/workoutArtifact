package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.endpoint.domain.Muscle;

public class Validator {

  public static boolean validateInputString(String s) {
    return s.matches("^[ A-Za-z]+$");
  }

  public static boolean validateMuscle(Muscle muscle) {
    if (muscle.getName() == null || muscle.getName().isEmpty()) { throw new MuscleException(ErrorCodes.MUSCLE_NAME_NOT_SET); }
    if (muscle.getIsUpperBody() == null) { throw new MuscleException(ErrorCodes.IS_UPPER_BODY_NOT_SET); }
    return true;
  }
}
