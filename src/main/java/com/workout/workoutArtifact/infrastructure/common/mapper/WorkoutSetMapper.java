package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetMapper {

  private final EntityManager entityManager;

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getId(),
        workoutSet.getSessionId(),
        workoutSet.getExerciseId(),
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        workoutSet.getSetNumber()
    );
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    return WorkoutSet.builder()
        .repetitions(workoutSetDto.getRepetitions())
        .weight(workoutSetDto.getWeight())
        .single(workoutSetDto.isSingle())
        .repetitionMaximum(workoutSetDto.getRepetitionMaximum())
        .setNumber(workoutSetDto.getSetNumber())
        .exerciseId(workoutSetDto.getExerciseId())
        .sessionId(workoutSetDto.getSessionId())
        .id(workoutSetDto.getId())
        .build();
  }

  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    return WorkoutSet.builder()
        .repetitions(workoutSetEntity.getRepetitions())
        .weight(workoutSetEntity.getWeight())
        .single(workoutSetEntity.isSingle())
        .repetitionMaximum(workoutSetEntity.getRepetitionMaximum())
        .exerciseId(workoutSetEntity.getExerciseEntity().getId())
        .id(workoutSetEntity.getId())
        .setNumber(workoutSetEntity.getSetNumber())
        .sessionId(workoutSetEntity.getSessionEntity().getId())
        .build();

  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setRepetitions(workoutSet.getRepetitions());
    workoutSetEntity.setWeight(workoutSet.getWeight());
    workoutSetEntity.setSingle(workoutSet.getSingle());
    workoutSetEntity.setRepetitionMaximum(workoutSet.getRepetitionMaximum());
    workoutSetEntity.setSetNumber(workoutSet.getSetNumber());
    workoutSetEntity.setId(workoutSet.getId());

    workoutSetEntity.setExerciseEntity(entityManager.getReference(ExerciseEntity.class, workoutSet.getExerciseId()));
    workoutSetEntity.setSessionEntity(entityManager.getReference(SessionEntity.class, workoutSet.getSessionId()));

    return workoutSetEntity;
  }

  public List<WorkoutSetEntity> toEntity(List<WorkoutSet> workoutSetList) {
    return workoutSetList.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

}
