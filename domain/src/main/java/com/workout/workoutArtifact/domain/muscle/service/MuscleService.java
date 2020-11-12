package com.workout.workoutArtifact.domain.muscle.service;

import com.workout.workoutArtifact.domain.muscle.model.MuscleRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuscleService {

  private final MuscleRepository muscleRepository;

  public String addMuscles(List<Muscle> muscles) {
    return muscleRepository.addMuscles(muscles);
  }

  public List<Muscle> getMuscles(Specification<Muscle> specification) {
    return muscleRepository.getMuscles(specification);
  }
}
