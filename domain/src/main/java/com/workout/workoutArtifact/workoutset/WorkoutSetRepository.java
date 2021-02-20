package com.workout.workoutArtifact.workoutset;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;

interface WorkoutSetRepository {

  List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification);
  List<UUID> addWorkoutSet(List<WorkoutSet> workoutSets);
  Boolean deleteWorkoutSet(UUID id);

  UUID save(WorkoutSet workoutSet);
}
