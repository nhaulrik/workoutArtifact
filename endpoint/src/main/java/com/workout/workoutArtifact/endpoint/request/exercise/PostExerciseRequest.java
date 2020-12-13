package com.workout.workoutArtifact.endpoint.request.exercise;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostExerciseRequest {

  private final UUID id;
  private final String name;
  private final String bodyPart;
  private final Boolean isCompound;
  private final List<UUID> muscleIds;
}
