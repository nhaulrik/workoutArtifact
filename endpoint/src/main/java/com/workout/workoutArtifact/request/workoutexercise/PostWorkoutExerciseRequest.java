package com.workout.workoutArtifact.request.workoutexercise;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class PostWorkoutExerciseRequest {

  private final UUID id;
  @NonNull
  private final Integer exerciseNumber;
  @NonNull
  private final UUID sessionId;
  @NonNull
  private final UUID exerciseId;
  @NonNull
  private final Boolean isWarmup;

}
