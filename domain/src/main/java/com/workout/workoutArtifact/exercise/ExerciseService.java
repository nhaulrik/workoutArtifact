package com.workout.workoutArtifact.exercise;

import com.workout.workoutArtifact.exercise.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.muscle.Muscle;
import com.workout.workoutArtifact.muscle.Muscle.IdsSpecification;
import com.workout.workoutArtifact.muscle.MuscleRepository;
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

  public UUID postExercise(UUID id, String name, String bodyPart, Boolean isCompound, List<UUID> muscleIds) {

    Optional<Exercise> exerciseOptional;
    if (id != null) {
      exerciseOptional = exerciseRepository.getExercises(new ExerciseIdSpecification(id)).stream().findFirst();
    } else {
      exerciseOptional = Optional.empty();
    }

    if (exerciseOptional.isPresent()) {
      Exercise exercise = exerciseOptional.get();

      if (name != null && !name.equals(exercise.getName())) {
        exercise.changeName(name);
      }
      if (bodyPart != null && !bodyPart.equals(exercise.getBodyPart())) {
        exercise.changeBodyPart(bodyPart);
      }
      if (isCompound != null && !isCompound.equals(exercise.getIsCompound())) {
        exercise.changeIsCompound(isCompound);
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
    } else {
      Exercise newExercise = Exercise.createExercise(name, isCompound, bodyPart);
      return exerciseRepository.save(newExercise);
    }
  }

  public Boolean deleteMuscleFromExercise(UUID exerciseId, UUID muscleId) {

    Optional<Exercise> exerciseOptional = exerciseRepository.getExercises(new ExerciseIdSpecification(exerciseId)).stream().findFirst();

    if (exerciseOptional.isPresent()) {
      Exercise exercise = exerciseOptional.get();

      exercise.removeMuscle(muscleId);
      return exerciseRepository.save(exercise) != null;
    } else {
      throw new RuntimeException(String.format("could not delete muscle with id: %s from exercise with id %s", muscleId, exerciseId));
    }
  }

  public Boolean deleteExercise(UUID exerciseId) {
    return exerciseRepository.delete(exerciseId);
  }
}
