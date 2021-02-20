package com.workout.workoutArtifact.muscle;

import com.workout.workoutArtifact.specification.Specification;
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
