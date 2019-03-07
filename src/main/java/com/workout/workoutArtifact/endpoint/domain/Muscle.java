package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import lombok.Data;

@Data
public class Muscle {

  @JsonProperty
  private String name;

  @JsonProperty
  private Boolean isUpperBody;

  @Override
  public String toString() {
    return name;
  }

}
