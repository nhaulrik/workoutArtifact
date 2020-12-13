package com.workout.workoutArtifact.domain.exercise.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.IdsSpecification;
import com.workout.workoutArtifact.domain.muscle.model.MuscleRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;
  private final MuscleRepository muscleRepository;

  public UUID postExercise(UUID id, String name, String bodyPart, List<UUID> muscleIds) {

    Optional<Exercise> exerciseOptional = exerciseRepository.getExercises(new ExerciseIdSpecification(id)).stream().findFirst();

    if (exerciseOptional.isPresent()) {
      Exercise exercise = exerciseOptional.get();

      if (name != null && !name.equals(exercise.getName())) {
        exercise.changeName(name);
      }
      if (bodyPart != null && !bodyPart.equals(exercise.getBodyPart())) {
        exercise.changeBodyPart(bodyPart);
      }

      muscleIds.forEach(muscleId -> {
        Optional<Muscle> muscleOptional = muscleRepository.getMuscles(new IdsSpecification(Arrays.asList(muscleId))).stream().findFirst();
        if (muscleOptional.isPresent()) {
          if (!exercise.getMuscle(muscleId).isPresent()) {
            exercise.addMuscle(muscleOptional.get());
          }
        }
      });
      return exerciseRepository.save(exercise);
    }
    throw new RuntimeException(String.format("could not post exercise with id: %s", id));
  }
}
