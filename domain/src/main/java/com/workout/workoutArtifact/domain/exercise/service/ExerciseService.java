package com.workout.workoutArtifact.domain.exercise.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;

  public String addExercises(List<Exercise> exerciseList) {
    exerciseRepository.addExercises(exerciseList);
    return "Exercises added: " + exerciseList.size() + ". " + exerciseList.toString();
  }

  public List<Exercise> getExercises(Specification<Exercise> exerciseSpecification) {
    return exerciseRepository.getExercises(exerciseSpecification);
  }
}
