package com.workout.workoutArtifact.vaadin.dto;

import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class ExerciseDto {

  @NonNull
  private String name;

  @NonNull
  String type;

  @NonNull
  private List<String> muscles;

  @NonNull
  private String bodyPart;

}
