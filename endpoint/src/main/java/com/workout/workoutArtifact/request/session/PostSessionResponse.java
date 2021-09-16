package com.workout.workoutArtifact.request.session;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostSessionResponse {

  private final List<UUID> postedSessionIds;

}
