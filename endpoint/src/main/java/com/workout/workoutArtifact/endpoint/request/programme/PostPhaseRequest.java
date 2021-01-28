package com.workout.workoutArtifact.endpoint.request.programme;

import java.util.List;
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
  private final List<UUID> splitIds;

}
