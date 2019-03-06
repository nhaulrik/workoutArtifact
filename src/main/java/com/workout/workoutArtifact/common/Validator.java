package com.workout.workoutArtifact.common;

public class Validator {

  public static boolean validateInputString(String s) {
    return s.matches("^[ A-Za-z]+$");
  }

}
