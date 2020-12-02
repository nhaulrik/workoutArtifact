package com.workout.workoutArtifact.domain.workoutExercise.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;

public interface WorkoutExerciseRepository {

  List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification);

}
