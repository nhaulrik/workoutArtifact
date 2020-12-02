package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutExerciseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseDtoMapper {

  public WorkoutExerciseDto toDto(WorkoutExercise workoutExercise) {
    return new WorkoutExerciseDto(workoutExercise.getId(), workoutExercise.getExerciseNumber(), workoutExercise.getWorkoutSets().stream().map(WorkoutSet::getId).collect(Collectors.toList()));
  }

  public WorkoutExercise toDomain(WorkoutExerciseDto workoutExerciseDto) {
    return WorkoutExercise.createWorkoutExercise(
        workoutExerciseDto.getId(),
        workoutExerciseDto.getExerciseNumber(),
        workoutExerciseDto.getWorkoutSetIds().stream().map(WorkoutSet::fromId).collect(Collectors.toList())
    );
  }


}
