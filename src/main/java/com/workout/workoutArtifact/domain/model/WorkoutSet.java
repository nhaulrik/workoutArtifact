package com.workout.workoutArtifact.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import javax.annotation.Nonnull;
import javax.xml.ws.BindingType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


@Data
@Builder
public class WorkoutSet {

  @JsonProperty
  private Long id;

  @JsonProperty
  @NonNull
  private String exerciseName;

  @JsonProperty
  @NonNull
  private int repetitions;

  @JsonProperty
  @NonNull
  private double weight;

  @JsonProperty
  @NonNull
  private Boolean single;

  @JsonProperty
  @NonNull
  private int repetitionMaximum;

  @JsonProperty
  @NonNull
  private Exercise exercise;

  @JsonProperty
  @NonNull
  private int set;
}
