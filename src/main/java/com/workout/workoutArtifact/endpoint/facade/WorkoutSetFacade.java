package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.mapper.WorkoutSetDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;
  private final WorkoutSetMapper workoutSetMapper;
  private final WorkoutSetDtoSpecificationMapper workoutSetDtoSpecificationMapper;

  public List<WorkoutSetDto> getWorkoutSets(AbstractSpecification<WorkoutSetDto> specification) {

    Specification<WorkoutSet> workoutSetSpecification = workoutSetDtoSpecificationMapper.toWorkoutSetSpecification(specification);
    return workoutSetService.getWorkoutSet(workoutSetSpecification).stream()
        .map(workoutSetMapper::toDto)
        .collect(Collectors.toList());
  }

  public void addWorkoutSet(WorkoutSetDto workoutSetDto) {
    workoutSetService.addWorkoutSet(workoutSetMapper.toDomain(workoutSetDto));
  }

}
