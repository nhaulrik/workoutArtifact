package com.workout.workoutArtifact;

public class MuscleException extends RuntimeException {

  public MuscleException(ErrorCodes errorCode) {
    super(errorCode.getMessage());
  }

}
