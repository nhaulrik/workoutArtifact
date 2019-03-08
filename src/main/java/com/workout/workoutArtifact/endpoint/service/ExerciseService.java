package com.workout.workoutArtifact.endpoint.service;

import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;

  @Autowired
  public ExerciseService(ExerciseRepository exerciseRepository) {
    this.exerciseRepository = exerciseRepository;
  }

  public void addExercise(String exerciseName, Boolean isMultiJoint) {

    ExerciseEntity exerciseEntity = ExerciseEntity.builder()
        .name(exerciseName)
        .isMultiJoint(isMultiJoint)
        .build();

    exerciseRepository.save(exerciseEntity);

  }


}
