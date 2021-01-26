package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.ProgrammeExercise;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.SplitEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseEntityMapper;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgrammeExerciseEntityMapper {

  private final ExerciseEntityMapper exerciseEntityMapper;
  private final EntityManager entityManager;

  public ProgrammeExercise toDomain(ProgrammeExerciseEntity programmeExerciseEntity) {
    return ProgrammeExercise.instantiate(
        programmeExerciseEntity.getId(),
        programmeExerciseEntity.getSplitEntity().getId(),
        exerciseEntityMapper.toDomainObject(programmeExerciseEntity.getExerciseEntity()),
        programmeExerciseEntity.getNumber(),
        programmeExerciseEntity.getRPE(),
        programmeExerciseEntity.getRepetitions(),
        programmeExerciseEntity.getSetAmount(),
        programmeExerciseEntity.getRest()
    );
  }

  public ProgrammeExerciseEntity toEntity(ProgrammeExercise programmeExercise) {
    return new ProgrammeExerciseEntity(
        programmeExercise.getId().toString(),
        programmeExercise.getNumber(),
        programmeExercise.getRPE(),
        programmeExercise.getRepetitions(),
        programmeExercise.getSetAmount(),
        programmeExercise.getRest(),
        entityManager.getReference(SplitEntity.class, programmeExercise.getSplitId().toString()),
        exerciseEntityMapper.toEntity(programmeExercise.getExercise())
    );
  }


}
