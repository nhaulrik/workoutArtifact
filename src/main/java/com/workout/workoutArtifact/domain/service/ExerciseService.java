package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.ExerciseRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;
  private final ExerciseMapper mapper;

  @Autowired
  public ExerciseService(
      ExerciseRepository exerciseRepository,
      ExerciseMapper exerciseMapper) {
    this.exerciseRepository = exerciseRepository;
    this.mapper = exerciseMapper;
  }

  public String addExercises(List<Exercise> exerciseList) {

    exerciseList.stream()
        .map(mapper::toEntity)
        .forEach(exerciseEntity -> exerciseRepository.save(exerciseEntity));

    return "Exercises added: " + exerciseList.size() + ". " + exerciseList.toString();
  }

  public List<Exercise> getExercises(List<String> exerciseNames) {

    List<ExerciseEntity> exerciseEntities = new ArrayList<>();
    for (String exerciseName : exerciseNames) {
      if (exerciseName.contains("*")) {
        exerciseEntities.addAll(exerciseRepository.findAll());
      } else {
        ExerciseEntity exerciseEntity = exerciseRepository.findFirstByName(exerciseName);
        exerciseEntities.add(exerciseEntity);
      }
    }

    return exerciseEntities.stream()
        .map(mapper::toDomainObject)
        .collect(Collectors.toList());
  }


}
