package com.workout.workoutArtifact;

public enum ErrorCodes {

  ILLEGAL_MUSCLE_NAME(0001, "Muscle name must only contain alphabetical words."),
  UNKNOWN_MUSCLE(0002, "Unknown muscle name encountered."),
  MUSCLE_NAME_NOT_SET(0003, "Muscle name not set."),
  BODY_PART_NOT_SET(0004, "BodyPart not found or set.");

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
