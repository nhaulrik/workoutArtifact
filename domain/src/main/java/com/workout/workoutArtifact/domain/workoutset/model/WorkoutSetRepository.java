package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;

public interface WorkoutSetRepository {

  List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification);
  List<Long> addWorkoutSet(List<WorkoutSet> workoutSets);

}
