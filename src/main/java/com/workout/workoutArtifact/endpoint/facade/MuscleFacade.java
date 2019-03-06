package com.workout.workoutArtifact.endpoint.facade;

import static com.workout.workoutArtifact.common.Validator.validateInputString;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
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

  public void addMuscle(String muscleName) throws MuscleException {
    if (validateInputString(muscleName)) {
      muscleService.addMuscle(muscleName.trim());
    } else {
      throw new MuscleException(ErrorCodes.ILLEGAL_MUSCLE_NAME);
    }
  }

  public List<MuscleDto> getMusclesByName(List<String> muscleNames) {

    muscleNames.forEach(muscleName -> validateInputString(muscleName));

    List<Muscle> muscles = muscleService.getMuscles(muscleNames);

    return muscles.stream()
        .map(Muscle::toDto)
        .collect(Collectors.toList());
  }
}
