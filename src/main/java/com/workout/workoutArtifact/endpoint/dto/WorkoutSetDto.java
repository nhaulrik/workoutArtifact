package com.workout.workoutArtifact.endpoint.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class WorkoutSetDto {

  private Long id;

  @NonNull
  private String exerciseName;

  @NonNull
  private int repetitions;

  @NonNull
  private String weight;

  @NonNull
  private Boolean single;

  @NonNull
  private int repetitionMaximum;

}
