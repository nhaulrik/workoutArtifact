package com.workout.workoutArtifact.request.workoutset;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostWorkoutSetResponse {

  private final List<UUID> postedWorkoutSetIds;


}
