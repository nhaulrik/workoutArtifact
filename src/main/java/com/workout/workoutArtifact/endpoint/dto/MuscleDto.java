package com.workout.workoutArtifact.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MuscleDto {

  @JsonProperty
  private String name;

}
