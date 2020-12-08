package com.workout.workoutArtifact.endpoint.request;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class CreateSessionResponse {

  private final List<UUID> createdSessionIds;

}
