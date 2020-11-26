package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.mapper.dto.WorkoutSetDtoMapper;
import com.workout.workoutArtifact.endpoint.mapper.specification.WorkoutSetDtoSpecificationMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;
  private final WorkoutSetDtoMapper workoutSetDtoMapper;
  private final WorkoutSetDtoSpecificationMapper workoutSetDtoSpecificationMapper;

  public List<WorkoutSetDto> getWorkoutSets(AbstractSpecification<WorkoutSetDto> specification) {

    Specification<WorkoutSet> workoutSetSpecification = workoutSetDtoSpecificationMapper.toWorkoutSetSpecification(specification);
    return workoutSetService.getWorkoutSet(workoutSetSpecification).stream()
        .map(workoutSetDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public UUID addWorkoutSet(WorkoutSetDto workoutSetDto) {
    UUID workoutSetId = workoutSetService.addWorkoutSet(workoutSetDtoMapper.toDomain(workoutSetDto));
    return workoutSetId;
  }

  public UUID postWorkoutSet(UUID id, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID exerciseId, UUID sessionId) {
   return workoutSetService.postWorkoutSet(
        id,
        setNumber,
        weight,
        repetitions,
        repetitionMaximum,
        single,
        exerciseId,
        sessionId
    );
  }

  public void deleteWorkoutSet(UUID id) {
    workoutSetService.deleteWorkoutSet(id);
  }
}
