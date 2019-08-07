package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFacade {

  private final ExerciseService exerciseService;
  private final ExerciseMapper mapper;

  public String addExercises(List<Exercise> exerciseList) {
    return exerciseService.addExercises(exerciseList);
  }

  public List<ExerciseDto> getExercises(List<String> exerciseNames) {
    return exerciseService.getExercises(exerciseNames).stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
