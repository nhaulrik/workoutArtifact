package com.workout.workoutArtifact.endpoint.service;

import com.workout.workoutArtifact.common.MuscleEnum;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.MuscleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleService {

  private final MuscleRepository muscleRepository;

  @Autowired
  public MuscleService(MuscleRepository muscleRepository) {
    this.muscleRepository = muscleRepository;
  }

  public String addMuscles(List<Muscle> muscles) {

    List<Muscle> knownMuscles = muscles.stream()
        .filter(muscle -> MuscleEnum.contains(muscle.getName()))
        .collect(Collectors.toList());

    knownMuscles.stream()
        .map(Muscle::toEntity)
        .forEach(muscleEntity -> muscleRepository.save(muscleEntity));

    return "Known muscles added: " + knownMuscles.size() + ". " + knownMuscles.toString();
  }

  public List<Muscle> getMuscles(List<String> muscleNames) {

    return null;
  }

}
