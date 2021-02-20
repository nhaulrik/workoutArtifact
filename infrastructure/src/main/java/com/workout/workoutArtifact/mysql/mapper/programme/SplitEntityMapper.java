package com.workout.workoutArtifact.mysql.mapper.programme;

import com.workout.workoutArtifact.programme.Split;
import com.workout.workoutArtifact.mysql.entity.programme.PhaseEntity;
import com.workout.workoutArtifact.mysql.entity.programme.SplitEntity;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SplitEntityMapper {

  private final SplitExerciseSetEntityMapper splitExerciseSetEntityMapper;
  private final EntityManager entityManager;

  public Split toDomain(SplitEntity splitEntity) {
    return Split.instantiate(
        splitEntity.getId(),
        splitEntity.getPhaseEntity().getId(),
        splitEntity.getNumber(),
        splitEntity.getWeek(),
        splitEntity.getName(),
        splitEntity.getDayOfWeek(),
        splitEntity.getCreationDateTime(),
        splitEntity.getSplitExerciseSets().stream().map(splitExerciseSetEntityMapper::toDomain).collect(Collectors.toList())
    );
  }

  public SplitEntity toEntity(Split split) {
    return new SplitEntity(
        split.getId().toString(),
        split.getNumber(),
        split.getWeek(),
        split.getName(),
        split.getDayOfWeek(),
        split.getCreationDateTime(),
        split.getSplitExerciseSets().stream().map(splitExerciseSetEntityMapper::toEntity).collect(Collectors.toList()),
        entityManager.getReference(PhaseEntity.class, split.getPhaseId())
    );
  }

}
