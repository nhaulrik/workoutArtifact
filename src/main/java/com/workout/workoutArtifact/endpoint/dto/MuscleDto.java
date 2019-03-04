package com.workout.workoutArtifact.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MuscleDto {

  @JsonProperty
  private String name;

}
