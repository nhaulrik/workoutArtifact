package com.workout.workoutArtifact;

public enum ErrorCodes {

  ILLEGAL_MUSCLE_NAME(0001, "Muscle name must only contain alphabetical words.");

  private int errorCode;
  private String message;

  ErrorCodes(int errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }


}
