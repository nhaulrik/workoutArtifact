package com.workout.workoutArtifact.workoutExercise;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;

interface WorkoutExerciseRepository {

  List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification);

  Boolean deleteWorkoutExercise(UUID id);

  UUID save(WorkoutExercise workoutExercise);
}
