package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.common.Validator;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import com.workout.workoutArtifact.vaadin.dto.MuscleDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleFacade {

  private final MuscleService muscleService;
  private final Mapper mapper;

  @Autowired
  public MuscleFacade(
      MuscleService muscleService,
      Mapper mapper) {
    this.muscleService = muscleService;
    this.mapper = mapper;
  }

  public String addMuscles(List<Muscle> muscles) throws MuscleException {
    muscles.forEach(muscle -> Validator.validateMuscle(muscle));
    return muscleService.addMuscles(muscles);
  }

  public List<MuscleDto> getMusclesByName(List<String> muscleNames) {
    return muscleService.getMuscles(muscleNames).stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
