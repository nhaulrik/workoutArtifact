package com.workout.workoutArtifact.application.exercise;

import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.exercise.ExerciseRepository;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetExercise {

  private final ExerciseRepository exerciseRepository;

  public List<Exercise> execute(AbstractSpecification specification) {
    return exerciseRepository.getExercises(specification);
  }

}
