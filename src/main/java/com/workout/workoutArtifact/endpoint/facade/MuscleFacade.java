package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleFacade {

  MuscleService muscleService;

  @Autowired
  public MuscleFacade(MuscleService muscleService) {
    this.muscleService = muscleService;
  }

  public void addMuscle(String muscleName) throws MuscleException {
    boolean onlyAlphabeticalAndSpacing = muscleName.matches("^[ A-Za-z]+$");
    if (onlyAlphabeticalAndSpacing) {
      muscleService.addMuscle(muscleName.trim());
    } else {
      throw new MuscleException(ErrorCodes.ILLEGAL_MUSCLE_NAME);
    }
  }
}
