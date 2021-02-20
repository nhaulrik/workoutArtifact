package com.workout.workoutArtifact.muscle;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

interface MuscleRepository {

  List<Muscle> getMuscles(Specification<Muscle> muscleSpecification);
  String addMuscles(List<Muscle> muscles);
}
