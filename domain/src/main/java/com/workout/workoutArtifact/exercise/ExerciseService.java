package com.workout.workoutArtifact.exercise;

import com.workout.workoutArtifact.exercise.Exercise.ExerciseIdsSpecification;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;

  public Map<UUID, String> getExerciseMap(List<UUID> exerciseIds) {
    Map<UUID, String> exerciseMap = new HashMap<>();

    exerciseRepository.getExercises(new ExerciseIdsSpecification(exerciseIds)).forEach(exercise -> exerciseMap.put(exercise.getId(), exercise.getName()));
    return exerciseMap;
  }

  public UUID postExercise(UUID id, String name, String bodyPart, Boolean isCompound, List<UUID> muscleIds) {

    Optional<Exercise> exerciseOptional = exerciseRepository.getExercises(new Exercise.ExerciseIdsSpecification(Arrays.asList(id))).stream().findFirst();

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

      return exerciseRepository.save(exercise);
    } else {
      throw new RuntimeException(String.format("exercise with id %s was not found", id));
    }
  }

  public Boolean deleteMuscleFromExercise(UUID exerciseId, UUID muscleId) {

    Optional<Exercise> exerciseOptional = exerciseRepository.getExercises(new ExerciseIdsSpecification(Arrays.asList(exerciseId))).stream().findFirst();

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

  public UUID createExercise(String name, String bodyPart, Boolean isCompound) {

    Exercise exercise = Exercise.createExercise(
        name,
        isCompound,
        bodyPart
    );

    return exerciseRepository.save(exercise);
  }
}
