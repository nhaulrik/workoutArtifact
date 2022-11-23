package com.workout.workoutArtifact.mysql.mapper.programme;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.entity.programme.SplitExerciseSetEntity;
import com.workout.workoutArtifact.programme.SplitExerciseSet;
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
        splitExerciseSet.getExerciseId().toString(),
        splitExerciseSet.getRepetitionMaximum(),
        entityManager.getReference(ExerciseEntity.class, splitExerciseSet.getExerciseId())
    );
  }


}
