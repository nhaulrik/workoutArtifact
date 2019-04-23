package com.workout.workoutArtifact.endpoint.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class WorkoutSetDto {

  @NonNull
  private String exerciseName;

  @NonNull
  private int repetitions;

  @NonNull
  private Boolean single;

  @NonNull
  private int repetitionMaximum;

}
