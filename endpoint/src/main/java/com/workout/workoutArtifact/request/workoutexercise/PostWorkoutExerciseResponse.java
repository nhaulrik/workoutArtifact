package com.workout.workoutArtifact.request.workoutexercise;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostWorkoutExerciseResponse {

  private final List<UUID> postedWorkoutExerciseIds;

}
