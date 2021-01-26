package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import java.util.UUID;

public class ProgrammeExercise {

  private final UUID id;
  private final Exercise exercise;
  private final Integer number;
  private final Integer RPE;
  private final Integer repetitions;
  private final Integer setAmount;
  private final Integer rest;

  private ProgrammeExercise(UUID id, Exercise exercise, Integer number, Integer RPE, Integer repetitions, Integer setAmount, Integer rest) {
    this.id = id;
    this.exercise = exercise;
    this.number = number;
    this.RPE = RPE;
    this.repetitions = repetitions;
    this.setAmount = setAmount;
    this.rest = rest;
  }
}
