package com.workout.workoutArtifact.endpoint.request.workoutexercise;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class PostWorkoutExerciseRequest {

  @NonNull
  private final UUID id;
  @NonNull
  private final Integer exerciseNumber;
  @NonNull
  private final UUID sessionId;
  @NonNull
  private final UUID exerciseId;

}
