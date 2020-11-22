package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetDtoMapper {

  private final ExerciseDtoMapper exerciseDtoMapper;

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getId(),
        workoutSet.getSessionId(),
        workoutSet.getExercise().getId(),
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
        .single(workoutSetDto.getSingle())
        .repetitionMaximum(workoutSetDto.getRepetitionMaximum())
        .setNumber(workoutSetDto.getSetNumber())
        .exercise(Exercise.fromId(workoutSetDto.getId()))
        .sessionId(workoutSetDto.getSessionId())
        .id(workoutSetDto.getId())
        .build();
  }

}
