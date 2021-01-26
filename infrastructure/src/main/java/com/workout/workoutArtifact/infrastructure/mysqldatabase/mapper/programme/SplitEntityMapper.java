package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.Split;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.PhaseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.SplitEntity;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SplitEntityMapper {

  private final ProgrammeExerciseEntityMapper programmeExerciseEntityMapper;
  private final EntityManager entityManager;

  public Split toDomain(SplitEntity splitEntity) {
    return Split.instantiate(
        splitEntity.getId(),
        splitEntity.getPhaseEntity().getId(),
        splitEntity.getNumber(),
        splitEntity.getWeek(),
        splitEntity.getDayOfWeek(),
        splitEntity.getCreationDateTime(),
        splitEntity.getProgrammeExerciseEntities().stream().map(programmeExerciseEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public SplitEntity toEntity(Split split) {
    return new SplitEntity(
        split.getId().toString(),
        split.getNumber(),
        split.getWeek(),
        split.getDayOfWeek(),
        split.getCreationDateTime(),
        split.getProgrammeExercises().stream().map(programmeExerciseEntityMapper::toEntity).collect(Collectors.toList()),
        entityManager.getReference(PhaseEntity.class, split.getPhaseId())
    );
  }

}
