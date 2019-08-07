package com.workout.workoutArtifact.domain.muscle.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface MuscleRepository {

  List<Muscle> getMuscles(Specification<Muscle> muscleSpecification);
  String addMuscles(List<Muscle> muscles);
}
