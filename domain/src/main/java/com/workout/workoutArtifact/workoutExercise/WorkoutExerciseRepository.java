package com.workout.workoutArtifact.workoutExercise;

import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise.IdsSpecification;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutExerciseRepository {

  List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification);

  Boolean deleteWorkoutExercise(UUID id);

  UUID save(WorkoutExercise workoutExercise);

  Optional<WorkoutExercise> getWorkoutExercise(Specification<WorkoutExercise> workoutExerciseSpecification);
}
