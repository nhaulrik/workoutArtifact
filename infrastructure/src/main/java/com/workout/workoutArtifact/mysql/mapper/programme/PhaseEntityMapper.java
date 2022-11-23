package com.workout.workoutArtifact.mysql.mapper.programme;

import com.workout.workoutArtifact.mysql.entity.programme.PhaseEntity;
import com.workout.workoutArtifact.mysql.entity.programme.ProgrammeEntity;
import com.workout.workoutArtifact.programme.Phase;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhaseEntityMapper {

  private final SplitEntityMapper splitEntityMapper;
  private final EntityManager entityManager;

  public Phase toDomain(PhaseEntity phaseEntity) {
    return Phase.instantiate(
        phaseEntity.getId(),
        phaseEntity.getProgrammeEntity().getId(),
        phaseEntity.getNumber(),
        phaseEntity.getName(),
        phaseEntity.getDescription(),
        phaseEntity.getSplitEntities().stream().map(splitEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public PhaseEntity toEntity(Phase phase) {
    return new PhaseEntity(
        phase.getId().toString(),
        phase.getNumber(),
        phase.getName(),
        phase.getDescription(),
        phase.getSplitList().stream().map(splitEntityMapper::toEntity).collect(Collectors.toList()),
        entityManager.getReference(ProgrammeEntity.class, phase.getProgrammeId().toString())
    );
  }

}
