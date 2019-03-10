package com.workout.workoutArtifact.common;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.endpoint.domain.Muscle;

public class Validator {

  public static boolean validateInputString(String s) {
    return s.matches("^[ A-Za-z]+$");
  }

  public static boolean validateMuscle(Muscle muscle) {
    if (muscle.toString() == null || muscle.toString().isEmpty()) { throw new MuscleException(ErrorCodes.MUSCLE_NAME_NOT_SET); }
    if (muscle.getBodyPart() == null) { throw new MuscleException(ErrorCodes.BODY_PART_NOT_SET); }
    return true;
  }
}
