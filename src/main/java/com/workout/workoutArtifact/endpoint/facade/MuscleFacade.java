package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.endpoint.service.MuscleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleFacade {

  MuscleService muscleService;

  @Autowired
  public MuscleFacade(MuscleService muscleService) {
    this.muscleService = muscleService;
  }

  public void addMuscle(String muscleName) {
    muscleService.addMuscle(muscleName);
  }
}
