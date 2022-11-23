package com.workout.workoutArtifact.application.workoutexercise;


import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.workoutExercise.WorkoutExerciseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetWorkoutExercise {

  private final WorkoutExerciseRepository workoutExerciseRepository;

  public List<WorkoutExercise> execute(Specification<WorkoutExercise> workoutExerciseSpecification) {
    return workoutExerciseRepository.getWorkoutExercises(workoutExerciseSpecification);
  }
}
