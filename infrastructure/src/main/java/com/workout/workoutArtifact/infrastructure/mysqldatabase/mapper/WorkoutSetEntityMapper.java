package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class WorkoutSetEntityMapper {

  private final EntityManager entityManager;

  @Transactional
  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    return WorkoutSet.initializeWorkoutSet(
        workoutSetEntity.getId(),
        workoutSetEntity.getWorkoutExerciseEntity().getId(),
        workoutSetEntity.getSingle(),
        workoutSetEntity.getWeight(),
        workoutSetEntity.getRepetitions(),
        workoutSetEntity.getRepetitionMaximum(),
        workoutSetEntity.getSetNumber());
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setRepetitions(workoutSet.getRepetitions());
    workoutSetEntity.setWeight(workoutSet.getWeight());
    workoutSetEntity.setSingle(workoutSet.getSingle());
    workoutSetEntity.setRepetitionMaximum(workoutSet.getRepetitionMaximum());
    workoutSetEntity.setSetNumber(workoutSet.getSetNumber());
    workoutSetEntity.setId(workoutSet.getId().toString());
    workoutSetEntity.setWorkoutExerciseEntity(entityManager.getReference(WorkoutExerciseEntity.class, workoutSet.getWorkoutExerciseId().toString()));

    return workoutSetEntity;
  }

  public List<WorkoutSetEntity> toEntity(List<WorkoutSet> workoutSetList) {
    return workoutSetList.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

}
