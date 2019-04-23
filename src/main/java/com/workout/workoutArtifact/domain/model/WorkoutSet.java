package com.workout.workoutArtifact.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import lombok.Data;
import lombok.NonNull;


@Data
public class WorkoutSet {


  public WorkoutSet(Exercise exercise, int repetitions, Boolean single) {
    this.exercise = exercise;
    this.exerciseName = exercise.getName();
    this.repetitions = repetitions;
    this.single = single;
  }

  @JsonProperty
  @NonNull
  private ExerciseEnum exerciseName;

  @JsonProperty
  @NonNull
  private int repetitions;

  @JsonProperty
  @NonNull
  private Boolean single;

  @JsonProperty
  @NonNull
  private Exercise exercise;

  @JsonProperty
  @NonNull
  private int repetitionMaximum;

}
