package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.service.ExerciseService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFacade {

  private final ExerciseService exerciseService;

  public ExerciseFacade(ExerciseService exerciseService) {
    this.exerciseService = exerciseService;
  }

  public String addExercises(List<Exercise> exerciseList) {
    return exerciseService.addExercises(exerciseList);
  }

  public List<Exercise> getExercises(List<String> exerciseNames) {
    return exerciseService.getExercises(exerciseNames);
  }
}
