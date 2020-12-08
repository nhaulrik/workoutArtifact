package com.workout.workoutArtifact.graphql.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MuscleDto {

  @NonNull
  private UUID id;
  @NonNull
  private String name;
  @NonNull
  private String bodyPart;
  @NonNull
  private List<UUID> exerciseIds;
}
