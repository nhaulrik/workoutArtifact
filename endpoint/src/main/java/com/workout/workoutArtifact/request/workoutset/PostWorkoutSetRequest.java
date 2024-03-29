package com.workout.workoutArtifact.request.workoutset;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class PostWorkoutSetRequest {

  private final UUID id;
  @NonNull
  private final UUID workoutExerciseId;
  @NonNull
  private final Integer repetitions;
  @NonNull
  private final Integer setNumber;
  @NonNull
  private final Double weight;

  private final Boolean single;
  private final Integer repetitionMaximum;
}
