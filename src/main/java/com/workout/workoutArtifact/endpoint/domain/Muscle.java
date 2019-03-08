package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
