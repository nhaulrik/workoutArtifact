package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetDtoMapper {

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getId(),
        workoutSet.getSessionId(),
        workoutSet.getExercise().getId(),
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        workoutSet.getSetNumber(),
        workoutSet.getCreatedTime()
    );
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    return WorkoutSet.initializeWorkoutSet(
        workoutSetDto.getId(),
        workoutSetDto.getSessionId(),
        Exercise.fromId(workoutSetDto.getExerciseId()),
        workoutSetDto.getSingle(),
        workoutSetDto.getWeight(),
        workoutSetDto.getRepetitions(),
        workoutSetDto.getRepetitionMaximum(),
        workoutSetDto.getSetNumber(),
        workoutSetDto.getCreatedTime()
    );
  }

}
