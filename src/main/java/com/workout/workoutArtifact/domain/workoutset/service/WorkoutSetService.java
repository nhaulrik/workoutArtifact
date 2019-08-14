package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetJpaRepository workoutSetRepository;
  private final WorkoutSetMapper workoutSetMapper;
  private final ExerciseRepository exerciseRepository;

  public List<WorkoutSet> getWorkoutSet() {
    List<WorkoutSetEntity> workoutSetEntities = new ArrayList<>();
    workoutSetEntities.addAll(workoutSetRepository.findAll());

    return workoutSetEntities.stream()
        .map(workoutSetMapper::toDomain)
        .collect(Collectors.toList());
  }

  public void addWorkoutSet(WorkoutSet workoutSet) {

   WorkoutSetEntity workoutSetEntity = workoutSetMapper.toEntity(workoutSet);
   Exercise exercise = exerciseRepository.getExercises(new Exercise.NameSpecification(
       Arrays.asList(workoutSet.getExerciseName()))).get(0);
//    workoutSetEntity.setExerciseEntity(exer);
    // TODO: 10-08-2019 fix this
   workoutSetRepository.save(workoutSetEntity);
  }

  public void removeWorkoutSet(WorkoutSet workoutSet) {
    workoutSetRepository.deleteById(workoutSet.getId());
  }
}
