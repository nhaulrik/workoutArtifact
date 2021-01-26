package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.Phase;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.PhaseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
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
        phaseEntity.getDescription(),
        phaseEntity.getSplitEntities().stream().map(splitEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public PhaseEntity toEntity(Phase phase) {
    return new PhaseEntity(
        phase.getId().toString(),
        phase.getNumber(),
        phase.getDescription(),
        phase.getSplitList().stream().map(splitEntityMapper::toEntity).collect(Collectors.toList()),
        entityManager.getReference(ProgrammeEntity.class, phase.getProgrammeId())
    );
  }

}
