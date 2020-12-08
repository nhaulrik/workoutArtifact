package com.workout.workoutArtifact.endpoint.request.session;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostSessionResponse {

  private final List<UUID> postedSessionIds;

}
