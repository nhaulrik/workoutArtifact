package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetMapper {

  private final ExerciseMapper exerciseMapper;
  private final ExerciseService exerciseService;

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    WorkoutSetDto workoutSetDto = WorkoutSetDto.builder()
    .exerciseName(workoutSet.getExerciseName())
        .repetitions(workoutSet.getRepetitions())
        .weight(workoutSet.getWeight())
        .single(workoutSet.getSingle())
        .repetitionMaximum(workoutSet.getRepetitionMaximum())
        .id(workoutSet.getId())
        .setNumber(workoutSet.getSetNumber())
        .build();

    return workoutSetDto;
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    Exercise exercise = exerciseService.getExercises(Arrays.asList(workoutSetDto.getExerciseName()))
        .get(0);
    WorkoutSet workoutSet = WorkoutSet.builder()
    .exerciseName(exercise.getName())
        .repetitions(workoutSetDto.getRepetitions())
        .weight(workoutSetDto.getWeight())
        .single(workoutSetDto.isSingle())
        .repetitionMaximum(workoutSetDto.getRepetitionMaximum())
        .setNumber(workoutSetDto.getSetNumber())
        .exercise(exercise)
        .id(workoutSetDto.getId())
        .build();

    return workoutSet;
  }

  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    WorkoutSet workoutSet = WorkoutSet.builder()
    .exerciseName(workoutSetEntity.getExerciseEntity().getName())
        .repetitions(workoutSetEntity.getRepetitions())
        .weight(workoutSetEntity.getWeight())
        .single(workoutSetEntity.isSingle())
        .repetitionMaximum(workoutSetEntity.getRepetitionMaximum())
        .exercise(exerciseMapper.toDomainObject(workoutSetEntity.getExerciseEntity()))
        .id(workoutSetEntity.getId())
        .setNumber(workoutSetEntity.getSetNumber())
        .build();

    return workoutSet;
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        workoutSet.getSetNumber()
    );
    workoutSetEntity.setId(workoutSet.getId());
    return workoutSetEntity;
  }

}
