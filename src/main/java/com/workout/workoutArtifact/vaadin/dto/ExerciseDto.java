package com.workout.workoutArtifact.vaadin.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ExerciseDto {

  @NonNull
  private String name;

  @NonNull
  String type;

  @NonNull
  private String muscles;

}
