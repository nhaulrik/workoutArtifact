package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.PhaseDto;
import com.workout.workoutArtifact.mysql.entity.programme.PhaseEntity;
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
