package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.SplitExerciseSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.SplitExerciseSetEntity;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SplitExerciseSetEntityMapper {

  private final EntityManager entityManager;

  public SplitExerciseSet toDomain(SplitExerciseSetEntity splitExerciseSetEntity) {
    return SplitExerciseSet.instantiate(
        splitExerciseSetEntity.getExerciseEntity().getId(),
        splitExerciseSetEntity.getRepetitionMaximum()
    );
  }

  public SplitExerciseSetEntity toEntity(SplitExerciseSet splitExerciseSet) {
    return new SplitExerciseSetEntity(
        splitExerciseSet.getRepetitionMaximum(),
        entityManager.getReference(ExerciseEntity.class, splitExerciseSet.getExerciseId())
    );
  }


}
