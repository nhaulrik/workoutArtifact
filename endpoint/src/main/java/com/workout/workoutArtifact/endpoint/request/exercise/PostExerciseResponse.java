package com.workout.workoutArtifact.endpoint.request.exercise;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostExerciseResponse {

  private final List<UUID> postedExerciseIds;

}
