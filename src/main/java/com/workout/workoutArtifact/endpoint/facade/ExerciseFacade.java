package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.endpoint.service.ExerciseService;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFacade {

  private final ExerciseService exerciseService;

  public ExerciseFacade(ExerciseService exerciseService) {
    this.exerciseService = exerciseService;
  }

  public void addExercise(String exerciseName, Boolean isMultiJoint) {

    exerciseService.addExercise(exerciseName, isMultiJoint);

  }


}
