package com.workout.workoutArtifact.programme;

import java.util.UUID;
import lombok.Getter;

@Getter
public class SplitExerciseSet {

  private final Integer repetitionMaximum;
  private final UUID exerciseId;

  private SplitExerciseSet(UUID exerciseId, Integer repetitionMaximum) {
    this.repetitionMaximum = repetitionMaximum;
    this.exerciseId = exerciseId;
  }

  public static SplitExerciseSet instantiate(UUID exerciseId, Integer repetitionMaximum) {
    return new SplitExerciseSet(exerciseId, repetitionMaximum);
  }
}
