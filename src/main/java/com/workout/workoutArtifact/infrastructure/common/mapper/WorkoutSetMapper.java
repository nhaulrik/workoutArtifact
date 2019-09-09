package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetMapper {

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return WorkoutSetDto.builder()
        .exerciseId(workoutSet.getExerciseId())
        .repetitions(workoutSet.getRepetitions())
        .weight(workoutSet.getWeight())
        .single(workoutSet.getSingle())
        .repetitionMaximum(workoutSet.getRepetitionMaximum())
        .id(workoutSet.getId())
        .setNumber(workoutSet.getSetNumber())
        .build();
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    return WorkoutSet.builder()
        .repetitions(workoutSetDto.getRepetitions())
        .weight(workoutSetDto.getWeight())
        .single(workoutSetDto.isSingle())
        .repetitionMaximum(workoutSetDto.getRepetitionMaximum())
        .setNumber(workoutSetDto.getSetNumber())
        .exerciseId(workoutSetDto.getExerciseId())
        .id(workoutSetDto.getId())
        .build();
  }

  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    return WorkoutSet.builder()
        .repetitions(workoutSetEntity.getRepetitions())
        .weight(workoutSetEntity.getWeight())
        .single(workoutSetEntity.isSingle())
        .repetitionMaximum(workoutSetEntity.getRepetitionMaximum())
        .exerciseId(workoutSetEntity.getExerciseId())
        .id(workoutSetEntity.getId())
        .setNumber(workoutSetEntity.getSetNumber())
        .build();
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        workoutSet.getSetNumber(),
        workoutSet.getExerciseId()
    );
    workoutSetEntity.setId(workoutSet.getId());
    return workoutSetEntity;
  }

  public List<WorkoutSetEntity> toEntity(List<WorkoutSet> workoutSetList) {
    return workoutSetList.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

}
