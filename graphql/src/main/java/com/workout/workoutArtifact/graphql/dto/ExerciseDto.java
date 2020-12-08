package com.workout.workoutArtifact.graphql.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ExerciseDto {

  @NonNull
  private UUID id;
  private String name;
  private String bodyPart;
  private Boolean isCompound;
  private List<UUID> muscleIds;

}
