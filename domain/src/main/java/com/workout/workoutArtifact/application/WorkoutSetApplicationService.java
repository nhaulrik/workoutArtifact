package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.workoutset.WorkoutSetService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorkoutSetApplicationService {

  private final WorkoutSetService workoutSetService;
  
  public UUID handleWorkoutSet(java.util.UUID id, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID workoutExerciseId) {

    if (id != null) {
      return workoutSetService.postWorkoutSet(
          id,
          setNumber,
          weight,
          repetitions,
          repetitionMaximum,
          single,
          workoutExerciseId
      );
    } else {
      return workoutSetService.createWorkoutSet(
          id,
          setNumber,
          weight,
          repetitions,
          repetitionMaximum,
          single,
          workoutExerciseId
      );
    }

  }

}
