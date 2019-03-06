package com.workout.workoutArtifact.endpoint.service;

import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import com.workout.workoutArtifact.mysqldatabase.MuscleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleService {

  private final MuscleRepository muscleRepository;

  @Autowired
  public MuscleService(MuscleRepository muscleRepository) {
    this.muscleRepository = muscleRepository;
  }

  public void addMuscle(String muscleName) {

    MuscleEntity muscle = MuscleEntity.builder()
        .name(muscleName)
        .build();

    muscleRepository.save(muscle);
  }

  public List<Muscle> getMuscles(List<String> muscleNames) {

  return null;
  }

}
