package com.workout.workoutArtifact.endpoint.dto.validator;

import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetDtoValidator {

  public boolean validateWorkoutSetDto(WorkoutSetDto workoutSetDto) {
    boolean isValid = true;
    if (workoutSetDto.getWeight() == 0) { isValid = false; }
    if (workoutSetDto.getRepetitions() == 0) {isValid = false; }
    if (workoutSetDto.getRepetitionMaximum() == 0) {isValid = false; }
//    if (Strings.isBlank(workoutSetDto.getExerciseName())) { isValid = false; }
    return isValid;
  }

}
