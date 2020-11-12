package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import org.springframework.stereotype.Component;

@Component
public class MuscleDtoMapper {

  public MuscleDto toDto(Muscle muscle) {
    return MuscleDto.builder()
        .name(muscle.getName())
        .bodyPart(muscle.getBodyPart())
        .id(muscle.getId())
        .build();
  }

  public Muscle toDomainObject(MuscleDto muscleDto) {
    return Muscle.builder()
        .id(muscleDto.getId())
        .name(muscleDto.getName())
        .bodyPart(muscleDto.getBodyPart())
        .build();
    }

}
