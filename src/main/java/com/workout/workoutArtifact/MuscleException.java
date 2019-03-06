package com.workout.workoutArtifact;

public class MuscleException extends RuntimeException {

  public MuscleException(ErrorCodes errorCode) {
    super(errorCode.getMessage());
  }

  public MuscleException(ErrorCodes errorCode, String s) {
    super(errorCode.getMessage() + s);
  }
}
