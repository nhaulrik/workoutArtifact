package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.service.MuscleService;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.mapper.MuscleDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuscleFacade {

  private final MuscleService muscleService;
  private final MuscleMapper muscleMapper;
  private final MuscleDtoSpecificationMapper muscleDtoSpecificationMapper;

  public String addMuscles(List<Muscle> muscles) throws MuscleException {
    return muscleService.addMuscles(muscles);
  }

  public List<MuscleDto> getMuscles(AbstractSpecification<MuscleDto> specification) {

    Specification<Muscle> muscleSpecification = muscleDtoSpecificationMapper.toMuscleSpecification(specification);

    return muscleService.getMuscles(muscleSpecification).stream()
        .filter(muscleSpecification::isSatisfiedBy)
        .map(muscleMapper::toDto)
        .collect(Collectors.toList());
  }

}
