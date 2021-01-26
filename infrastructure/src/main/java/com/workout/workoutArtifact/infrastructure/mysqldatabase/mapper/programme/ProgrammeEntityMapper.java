package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.Programme;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
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
        programmeEntity.getCreationDateTime(),
        programmeEntity.getPhaseEntities().stream().map(phaseEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public ProgrammeEntity toEntity(Programme programme) {
    return new ProgrammeEntity(
        programme.getId().toString(),
        programme.getCreationDateTime(),
        programme.getPhases().stream().map(phaseEntityMapper::toEntity).collect(Collectors.toList())
    );
  }

}
