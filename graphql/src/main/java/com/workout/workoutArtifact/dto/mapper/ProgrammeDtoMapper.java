package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.ProgrammeDto;
import com.workout.workoutArtifact.mysql.entity.programme.ProgrammeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeDtoMapper {

  public ProgrammeDto toDto(ProgrammeEntity programmeEntity) {
    return new ProgrammeDto(
        programmeEntity.getId(),
        programmeEntity.getName(),
        programmeEntity.getDescription()
    );
  }

}
