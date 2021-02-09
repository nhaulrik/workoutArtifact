package com.workout.workoutArtifact.graphql.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ProgrammeDto {

  @NonNull
  private UUID id;
  @NonNull
  private String name;
  @NonNull
  private String description;

}
