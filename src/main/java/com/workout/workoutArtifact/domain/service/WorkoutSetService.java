package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.WorkoutSetRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;
  private final WorkoutSetMapper workoutSetMapper;

  @Autowired
  public WorkoutSetService(
      WorkoutSetRepository workoutSetRepository,
      WorkoutSetMapper workoutSetMapper) {
    this.workoutSetRepository = workoutSetRepository;
    this.workoutSetMapper = workoutSetMapper;
  }

  public List<WorkoutSet> getWorkoutSet() {
    List<WorkoutSetEntity> workoutSetEntities = new ArrayList<>();
    workoutSetEntities.addAll(workoutSetRepository.findAll());

    return workoutSetEntities.stream()
        .map(workoutSetMapper::toDomain)
        .collect(Collectors.toList());
  }

  public void addWorkoutSets(List<WorkoutSet> workoutSets) {
    workoutSets.stream()
        .map(workoutSetMapper::toEntity)
        .forEach(workoutSetRepository::save);
  }
}
