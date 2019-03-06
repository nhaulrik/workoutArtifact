package com.workout.workoutArtifact.endpoint.domain;

import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Muscle {

  private String name;

  public static MuscleDto toDto(Muscle muscle) {
    return MuscleDto.builder()
        .name(muscle.name)
        .build();
  }

}
