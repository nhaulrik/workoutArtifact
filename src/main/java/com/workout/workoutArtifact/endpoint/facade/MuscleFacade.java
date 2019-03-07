package com.workout.workoutArtifact.endpoint.facade;

import static com.workout.workoutArtifact.common.Validator.validateInputString;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.common.Validator;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleFacade {

  private final MuscleService muscleService;

  @Autowired
  public MuscleFacade(MuscleService muscleService) {
    this.muscleService = muscleService;
  }

  public String addMuscles(List<Muscle> muscles) throws MuscleException {
    muscles.forEach(muscle -> Validator.validateMuscle(muscle));
    return muscleService.addMuscles(muscles);
  }

  public List<Muscle> getMusclesByName(List<String> muscleNames) {

    List<String> invalidMuscleNames = muscleNames.stream()
        .filter(muscleName -> !validateInputString(muscleName))
        .collect(Collectors.toList());

    if (!invalidMuscleNames.isEmpty()) {
      throw new MuscleException(ErrorCodes.ILLEGAL_MUSCLE_NAME, muscleNames.toString());
    }

    return muscleService.getMuscles(muscleNames);
  }
}
