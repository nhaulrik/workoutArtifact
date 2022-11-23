package com.workout.workoutArtifact.application.workoutset;

import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.workoutExercise.WorkoutExerciseRepository;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteWorkoutSet {

  private final WorkoutExerciseRepository workoutExerciseRepository;


  public Boolean execute(UUID workoutExerciseId, UUID id) {
    Optional<WorkoutExercise> workoutExerciseOptional = workoutExerciseRepository.getWorkoutExercise(new WorkoutExercise.IdsSpecification(Arrays.asList(workoutExerciseId)));

    if (workoutExerciseOptional.isPresent()) {
      WorkoutExercise workoutExercise = workoutExerciseOptional.get();
      workoutExercise.removeWorkoutset(id);

      workoutExerciseRepository.save(workoutExercise);
      return true;
    } else {
      throw new RuntimeException(String.format("workoutExercise with id %s was not found", workoutExerciseId));
    }
  }
}
