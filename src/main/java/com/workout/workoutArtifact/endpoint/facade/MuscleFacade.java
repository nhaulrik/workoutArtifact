package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.service.MuscleService;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.common.Validator;
import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuscleFacade {

  private final MuscleService muscleService;
  private final MuscleMapper muscleMapper;

  public String addMuscles(List<Muscle> muscles) throws MuscleException {
    muscles.forEach(muscle -> Validator.validateMuscle(muscle));
    return muscleService.addMuscles(muscles);
  }

  public List<MuscleDto> getMuscles(AbstractSpecification<Muscle> specification) {
    return muscleService.getMuscles(specification).stream()
        .filter(specification::isSatisfiedBy)
        .map(muscleMapper::toDto)
        .collect(Collectors.toList());
  }

}
