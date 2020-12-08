package com.workout.workoutArtifact.endpoint.request.workoutset;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class PostWorkoutSetRequest {

  @NonNull
  private final UUID id;
  @NonNull
  private final UUID workoutExerciseId;
  @NonNull
  private final Integer repetitions;
  @NonNull
  private final Integer repetitionMaximum;
  @NonNull
  private final Integer setNumber;
  @NonNull
  private final Double weight;
  @NonNull
  private final Boolean single;

}
