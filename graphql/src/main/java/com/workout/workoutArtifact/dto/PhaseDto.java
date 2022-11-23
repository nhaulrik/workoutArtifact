package com.workout.workoutArtifact.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PhaseDto {

  @NonNull
  private final UUID id;
  private Integer number;
  private String name;
  private String description;

}
