package com.workout.workoutArtifact.graphql.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class WorkoutSetDto {

  @NonNull
  private UUID id;

  private Integer repetitions;

  private Double weight;

  private Boolean single;

  private Integer repetitionMaximum;

  private Integer setNumber;

  private UUID workoutExerciseId;

}
