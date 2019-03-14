package com.workout.workoutArtifact.endpoint.service;

import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.specification.ExerciseSpecification;
import com.workout.workoutArtifact.endpoint.specification.ExerciseSpecification.SearchCriteria;
import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.ExerciseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;
  private final Mapper mapper;

  @Autowired
  public ExerciseService(
      ExerciseRepository exerciseRepository,
      Mapper mapper) {
    this.exerciseRepository = exerciseRepository;
    this.mapper = mapper;
  }

  public String addExercises(
      List<Exercise> exerciseList) {

    exerciseList.stream()
        .map(mapper::toEntity)
        .forEach(exerciseEntity -> exerciseRepository.save(exerciseEntity));

    return "Exercises added: " + exerciseList.size() + ". " + exerciseList.toString();
  }

  public List<Exercise> getExercises(List<String> exerciseNames) {

    List<ExerciseEntity> exerciseEntities = new ArrayList<>();
// TODO: 13-03-2019 test this
    for (String exerciseName : exerciseNames) {
      if (exerciseName.contains("*")) {
        exerciseEntities.addAll(exerciseRepository.findAll());
      } else {
        ExerciseSpecification exerciseSpecification = new ExerciseSpecification(
            new SearchCriteria("name", ":", exerciseName));

        exerciseEntities.addAll(exerciseRepository.findAll(exerciseSpecification));
      }
    }

    return exerciseEntities.stream()
        .map(mapper::toDomainObject)
        .collect(Collectors.toList());
  }


}
