package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface ExerciseRepository {

  List<Exercise> getExercises(Specification<Exercise> exerciseSpecification);
  String addExercises(List<Exercise> exercises);

  UUID save(Exercise exercise);

  Boolean delete(UUID exerciseId);
}
