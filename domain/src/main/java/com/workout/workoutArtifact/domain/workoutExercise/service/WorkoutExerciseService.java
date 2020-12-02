package com.workout.workoutArtifact.domain.workoutExercise.service;

import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExerciseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseService {

  private final WorkoutExerciseRepository workoutExerciseRepository;

  public List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification) {
    return workoutExerciseRepository.getWorkoutExercises(workoutExerciseSpecification);
  }


}
