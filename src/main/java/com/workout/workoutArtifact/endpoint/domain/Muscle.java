package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Muscle {

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
