package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.MuscleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Muscle {

  public String getName() {
    return muscle.toString();
  }

  @NonNull
  @JsonProperty (value = "name")
  private MuscleEnum muscle;

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPart;

}
