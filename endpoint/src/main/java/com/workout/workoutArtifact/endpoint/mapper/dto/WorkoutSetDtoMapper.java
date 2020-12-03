package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetDtoMapper {

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getId(),
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        workoutSet.getSetNumber(),
        workoutSet.getWorkoutExerciseId()
    );
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    return WorkoutSet.initializeWorkoutSet(
        workoutSetDto.getId(),
        workoutSetDto.getWorkoutExerciseId(),
        workoutSetDto.getSingle(),
        workoutSetDto.getWeight(),
        workoutSetDto.getRepetitions(),
        workoutSetDto.getRepetitionMaximum(),
        workoutSetDto.getSetNumber()
    );
  }

}
