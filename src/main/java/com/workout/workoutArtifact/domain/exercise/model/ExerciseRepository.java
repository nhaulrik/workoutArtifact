package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface ExerciseRepository {

  List<Exercise> getExercises(Specification<Exercise> exerciseSpecification);
  String addExercises(List<Exercise> exercises);

}
