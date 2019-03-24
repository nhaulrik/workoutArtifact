package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.backend.common.mapper.Mapper;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFacade {

  private final ExerciseService exerciseService;
  private final Mapper mapper;

  public ExerciseFacade(
      ExerciseService exerciseService,
      Mapper mapper) {
    this.exerciseService = exerciseService;
    this.mapper = mapper;
  }

  public String addExercises(List<Exercise> exerciseList) {
    return exerciseService.addExercises(exerciseList);
  }

  public List<ExerciseDto> getExercises(List<String> exerciseNames) {
    return exerciseService.getExercises(exerciseNames).stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
