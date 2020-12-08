package com.workout.workoutArtifact.domain.workoutExercise.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface WorkoutExerciseRepository {

  List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification);

  Boolean deleteWorkoutExercise(UUID id);

  UUID save(WorkoutExercise workoutExercise);
}
