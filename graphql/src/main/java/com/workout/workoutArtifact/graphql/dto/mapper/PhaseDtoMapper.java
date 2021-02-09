package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.PhaseDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.PhaseEntity;
import org.springframework.stereotype.Component;

@Component
public class PhaseDtoMapper {

  public PhaseDto toDto(PhaseEntity phaseEntity) {
    return new PhaseDto(
        phaseEntity.getId(),
        phaseEntity.getNumber(),
        phaseEntity.getName(),
        phaseEntity.getDescription()
    );
  }

}
