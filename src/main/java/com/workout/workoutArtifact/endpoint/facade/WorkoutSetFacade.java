package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.backend.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;
  private final WorkoutSetMapper workoutSetMapper;

  public WorkoutSetFacade(
      WorkoutSetService workoutSetService,
      WorkoutSetMapper workoutSetMapper) {
    this.workoutSetService = workoutSetService;
    this.workoutSetMapper = workoutSetMapper;
  }

  public List<WorkoutSetDto> getWorkoutSets() {
    return workoutSetService.getWorkoutSet().stream()
        .map(workoutSetMapper::toDto)
        .collect(Collectors.toList());
  }

  public void addWorkoutSet(List<WorkoutSet> workoutSets) {
    workoutSetService.addWorkoutSets(workoutSets);
  }

}
