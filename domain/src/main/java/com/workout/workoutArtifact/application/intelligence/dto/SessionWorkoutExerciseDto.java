package com.workout.workoutArtifact.application.intelligence.dto;

import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Value;

@Value
public class SessionWorkoutExerciseDto {
  private final String name;
  private final Double totalWeight;
  private final Integer totalRepetitions;

  public static List<SessionWorkoutExerciseDto> fromWorkoutExercises(List<WorkoutExercise> workoutExercises) {
    List<SessionWorkoutExerciseDto> workoutExerciseDtos = new ArrayList<>();
    workoutExerciseDtos.addAll(
        workoutExercises.stream()
            .map(workoutExercise -> new SessionWorkoutExerciseDto(
                    workoutExercise.getExerciseId().toString(),
                    workoutExercise.getTotalWorkoutExerciseWeight(),
                    workoutExercise.getTotalRepetitions()
                )
            )
            .collect(Collectors.toList())
    );
    return workoutExerciseDtos;
  }
}
