package com.workout.workoutArtifact.application.workoutexercise;

import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise.IdsSpecification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExerciseRepository;
import com.workout.workoutArtifact.workoutset.WorkoutSet;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostWorkoutSet {

  private final WorkoutExerciseRepository workoutExerciseRepository;

  public UUID execute(UUID id, Integer repetitions, Double weight, Integer setNumber, Integer repetitionMaximum, Boolean single, UUID workoutExerciseId) {

    Optional<WorkoutExercise> workoutExerciseOptional = workoutExerciseRepository.getWorkoutExercise(new IdsSpecification(Arrays.asList(workoutExerciseId)));

    if (workoutExerciseOptional.isPresent()) {
      WorkoutExercise workoutExercise = workoutExerciseOptional.get();

      Optional<WorkoutSet> workoutSetOptional = workoutExercise.getWorkoutSet(id);
      WorkoutSet workoutSet;
      if (workoutSetOptional.isPresent()) {
        workoutSet = workoutSetOptional.get();

        workoutSet.changeWeight(weight);
        workoutSet.changeRepetitions(repetitions);
        workoutSet.changeIsSingle(single);
        workoutSet.changeRepetitionMaximum(repetitionMaximum);
        workoutSet.changeSetNumber(repetitionMaximum);
      } else {
        workoutSet = WorkoutSet.createWorkoutSet(
            workoutExerciseId,
            single,
            weight,
            repetitions,
            repetitionMaximum,
            setNumber
        );
        workoutExercise.addWorkoutSet(workoutSet);
      }
      workoutExerciseRepository.save(workoutExercise);
      return workoutSet.getId();
    }
    throw new RuntimeException(String.format("WorkoutExercise with Id %s was not found", workoutExerciseId));
  }
}
