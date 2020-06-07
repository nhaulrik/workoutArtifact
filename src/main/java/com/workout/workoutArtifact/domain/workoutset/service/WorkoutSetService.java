package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;

  public List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification) {
    return workoutSetRepository.getWorkoutSet(workoutSetSpecification);
  }

  public Long addWorkoutSet(WorkoutSet workoutSet) {
   Long workoutSetId = workoutSetRepository.addWorkoutSet(Arrays.asList(workoutSet)).get(0);
   return workoutSetId;
  }

}
