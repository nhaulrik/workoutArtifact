package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.backend.common.Validator;
import com.workout.workoutArtifact.backend.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.domain.service.MuscleService;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

  public List<MuscleDto> getMusclesByName(List<String> muscleNames) {
    return muscleService.getMuscles(muscleNames).stream()
        .map(muscleMapper::toDto)
        .collect(Collectors.toList());
  }
}
