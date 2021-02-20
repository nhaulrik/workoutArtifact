package com.workout.workoutArtifact.mysql.mapper.programme;

import com.workout.workoutArtifact.programme.Programme;
import com.workout.workoutArtifact.mysql.entity.programme.ProgrammeEntity;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgrammeEntityMapper {

  private final PhaseEntityMapper phaseEntityMapper;

  public Programme toDomain(ProgrammeEntity programmeEntity) {
    return Programme.instantiate(
        programmeEntity.getId(),
        programmeEntity.getName(),
        programmeEntity.getDescription(),
        programmeEntity.getCreationDate(),
        programmeEntity.getPhaseEntities().stream().map(phaseEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public ProgrammeEntity toEntity(Programme programme) {
    return new ProgrammeEntity(
        programme.getId().toString(),
        programme.getName(),
        programme.getDescription(),
        programme.getCreationDate(),
        programme.getPhases().stream().map(phaseEntityMapper::toEntity).collect(Collectors.toList())
    );
  }

}
