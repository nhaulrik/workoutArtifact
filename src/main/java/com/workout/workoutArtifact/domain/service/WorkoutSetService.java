package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.ExerciseRepository;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.WorkoutSetRepository;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;
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
   workoutSetEntity.setExerciseEntity(exerciseRepository.findFirstByName(workoutSet.getExerciseName()));
   workoutSetRepository.save(workoutSetEntity);
  }

  public void removeWorkoutSet(WorkoutSet workoutSet) {
    workoutSetRepository.deleteById(workoutSet.getId());
  }
}
