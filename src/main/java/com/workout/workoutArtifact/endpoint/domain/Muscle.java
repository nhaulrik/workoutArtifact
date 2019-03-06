package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Muscle {

  @NonNull
  @JsonProperty
  private String name;

  public static MuscleEntity toEntity(Muscle muscle) {
    return MuscleEntity.builder()
        .name(muscle.name)
        .build();
  }

  @Override
  public String toString() {
    return name;
  }

}
