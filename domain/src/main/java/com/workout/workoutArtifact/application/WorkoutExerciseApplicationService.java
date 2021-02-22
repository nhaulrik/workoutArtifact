package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.workoutExercise.WorkoutExerciseService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseApplicationService {

  private final WorkoutExerciseService workoutExerciseService;

  public UUID handleWorkoutExercise(UUID id, UUID exerciseId, Integer exerciseNumber, UUID sessionId, Boolean isWarmup) {

    if (id != null) {
      return workoutExerciseService.postWorkoutExercise(id, exerciseId, exerciseNumber, isWarmup);

    } else {
      return workoutExerciseService.createWorkoutExercise(exerciseId, exerciseNumber, isWarmup, sessionId);
    }


  }
}
