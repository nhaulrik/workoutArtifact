package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.exercise.ExerciseService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseApplicationService {

  private final ExerciseService exerciseService;

  public UUID handleExercise(UUID id, String name, String bodyPart, Boolean isCompound, List<UUID> muscleIds) {
    if (id != null) {
      return exerciseService.postExercise(id, name, bodyPart, isCompound, muscleIds);
    } else {
      return exerciseService.createExercise(name, bodyPart, isCompound);
    }
  }

}
