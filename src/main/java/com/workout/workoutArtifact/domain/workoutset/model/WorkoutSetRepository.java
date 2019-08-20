package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface WorkoutSetRepository {

  List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification);
  String addWorkoutSet(List<WorkoutSet> workoutSets);

}
