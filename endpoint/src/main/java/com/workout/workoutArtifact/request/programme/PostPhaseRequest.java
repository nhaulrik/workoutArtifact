package com.workout.workoutArtifact.request.programme;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPhaseRequest {

  private final UUID id;
  private final UUID programmeId;
  private final Integer number;
  private final String name;
  private final String description;

}
