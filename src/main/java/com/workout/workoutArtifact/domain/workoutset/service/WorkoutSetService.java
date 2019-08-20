package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;
  private final ExerciseRepository exerciseRepository; // TODO: 14-08-2019 should not rely on any other repository. Consider an application service for this

  public List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification) {
    return workoutSetRepository.getWorkoutSet(workoutSetSpecification);
  }

  public void addWorkoutSet(WorkoutSet workoutSet) {
    workoutSet.setExercise(exerciseRepository.getExercises(new Exercise.NameSpecification(Arrays.asList(workoutSet.getExerciseName()))).get(0));
   workoutSetRepository.addWorkoutSet(Arrays.asList(workoutSet));
  }

  public String removeWorkoutSet(WorkoutSet workoutSet) {
    return "not Implemented yet";
  }

}
