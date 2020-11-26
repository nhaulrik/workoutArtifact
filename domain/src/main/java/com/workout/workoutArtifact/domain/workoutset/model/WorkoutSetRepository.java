package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface WorkoutSetRepository {

  List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification);
  List<UUID> addWorkoutSet(List<WorkoutSet> workoutSets);
  void deleteWorkoutSet(UUID id);
}
