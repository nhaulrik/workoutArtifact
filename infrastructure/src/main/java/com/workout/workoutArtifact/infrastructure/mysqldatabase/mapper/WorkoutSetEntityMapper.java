package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class WorkoutSetEntityMapper {

  private final ExerciseEntityMapper exerciseEntityMapper;

  @Transactional
  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    return WorkoutSet.initializeWorkoutSet(
        UUID.fromString(workoutSetEntity.getId()),
        workoutSetEntity.getSessionEntity().getId(),
        exerciseEntityMapper.toDomainObject(workoutSetEntity.getExerciseEntity()),
        workoutSetEntity.getSingle(),
        workoutSetEntity.getWeight(),
        workoutSetEntity.getRepetitions(),
        workoutSetEntity.getRepetitionMaximum(),
        workoutSetEntity.getSetNumber(),
        workoutSetEntity.getCreatedTime());
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setRepetitions(workoutSet.getRepetitions());
    workoutSetEntity.setWeight(workoutSet.getWeight());
    workoutSetEntity.setSingle(workoutSet.getSingle());
    workoutSetEntity.setRepetitionMaximum(workoutSet.getRepetitionMaximum());
    workoutSetEntity.setSetNumber(workoutSet.getSetNumber());
    workoutSetEntity.setId(workoutSet.getId().toString());
    workoutSetEntity.setCreatedTime(workoutSet.getCreatedTime());

    workoutSetEntity.setExerciseEntity(exerciseEntityMapper.toEntity(workoutSet.getExercise()));
//    workoutSetEntity.setSessionEntity(workoutSet.getSession()));

    return workoutSetEntity;
  }

  public List<WorkoutSetEntity> toEntity(List<WorkoutSet> workoutSetList) {
    return workoutSetList.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

}
