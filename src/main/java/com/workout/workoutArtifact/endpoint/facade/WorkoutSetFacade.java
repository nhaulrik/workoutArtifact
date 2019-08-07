package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.domain.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;
  private final WorkoutSetMapper workoutSetMapper;

  public List<WorkoutSetDto> getWorkoutSets() {
    return workoutSetService.getWorkoutSet().stream()
        .map(workoutSetMapper::toDto)
        .collect(Collectors.toList());
  }

  public void addWorkoutSet(WorkoutSetDto workoutSetDto) {
    workoutSetService.addWorkoutSet(workoutSetMapper.toDomain(workoutSetDto));
  }

  public void removeWorkoutSet(WorkoutSetDto workoutSetDto) {
    workoutSetService.removeWorkoutSet(workoutSetMapper.toDomain(workoutSetDto));
  }
}
