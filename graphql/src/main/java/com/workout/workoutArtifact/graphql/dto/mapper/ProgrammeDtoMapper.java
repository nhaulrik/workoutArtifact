package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.ProgrammeDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
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
