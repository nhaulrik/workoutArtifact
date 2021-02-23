package com.workout.workoutArtifact.workoutExercise;

import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise.IdsSpecification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseService {

  private final WorkoutExerciseRepository workoutExerciseRepository;

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseRepository.deleteWorkoutExercise(id);
  }

  public Optional<WorkoutExercise> getWorkoutExercise(Specification<WorkoutExercise> workoutExerciseSpecification) {
    return workoutExerciseRepository.getWorkoutExercises(workoutExerciseSpecification).stream().findFirst();
  }

  public UUID postWorkoutExercise(UUID id, UUID exerciseId, Integer exerciseNumber, Boolean isWarmup) {

    Optional<WorkoutExercise> workoutExerciseOptional = workoutExerciseRepository.getWorkoutExercises(new IdsSpecification(Arrays.asList(id))).stream().findFirst();

    if (workoutExerciseOptional.isPresent()) {
      WorkoutExercise workoutExercise = workoutExerciseOptional.get();
      if (exerciseNumber != null && exerciseNumber != workoutExercise.getExerciseNumber()) {
        workoutExercise.changeExerciseNumber(exerciseNumber);
        workoutExercise.changeIsWarmup(isWarmup);
      }
      if (workoutExercise.getExerciseId() != exerciseId) {
        workoutExercise.changeExercise(exerciseId);
      }
      return workoutExerciseRepository.save(workoutExercise);
    }
    throw new RuntimeException(String.format("workoutExercise with Id %s was not found", id));
  }

  public UUID createWorkoutExercise(UUID exerciseId, Integer exerciseNumber, Boolean isWarmup, UUID sessionId) {
    WorkoutExercise workoutExercise = WorkoutExercise.createWorkoutExercise(
        exerciseNumber, new ArrayList<>(), exerciseId, isWarmup, sessionId
    );

    return workoutExerciseRepository.save(workoutExercise);
  }
}
