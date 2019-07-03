package com.workout.workoutArtifact.endpoint.dto;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class WorkoutSetDto {

  private Long id;

  @NonNull
  private String exerciseName;

  @NonNull
  private int repetitions;

  @NonNull
  private double weight;

  @NonNull
  private boolean single;

  @NonNull
  private int repetitionMaximum;

  @NonNull
  private int setNumber;

}
