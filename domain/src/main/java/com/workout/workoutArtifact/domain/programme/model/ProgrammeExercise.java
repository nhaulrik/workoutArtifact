package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProgrammeExercise {

  private final UUID id;
  private final Exercise exercise;
  private final Integer number;
  private final Integer RPE;
  private final Integer repetitions;
  private final Integer setAmount;
  private final Integer rest;

  private final UUID splitId;

  private ProgrammeExercise(UUID id, UUID splitId, Exercise exercise, Integer number, Integer RPE, Integer repetitions, Integer setAmount, Integer rest) {
    this.id = id;
    this.splitId = splitId;
    this.exercise = exercise;
    this.number = number;
    this.RPE = RPE;
    this.repetitions = repetitions;
    this.setAmount = setAmount;
    this.rest = rest;
  }

  public static ProgrammeExercise instantiate(UUID id, UUID splitId, Exercise exercise, Integer number, Integer RPE, Integer repetitions, Integer setAmount, Integer rest) {
    return new ProgrammeExercise(id, splitId, exercise, number, RPE, repetitions, setAmount, rest);
  }

}
