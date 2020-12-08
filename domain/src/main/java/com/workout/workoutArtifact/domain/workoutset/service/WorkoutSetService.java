package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.IdsSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;

  public UUID postWorkoutSet(UUID id, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID workoutExerciseId) {

    Optional<WorkoutSet> workoutSetOptional = workoutSetRepository.getWorkoutSet(new IdsSpecification(Arrays.asList(id))).stream().findFirst();

    WorkoutSet workoutSet;
    if (workoutSetOptional.isPresent()) {
      workoutSet = workoutSetOptional.get();
      if (setNumber != null && setNumber != workoutSet.getSetNumber()) { workoutSet.changeSetNumber(setNumber); }
      if (weight != null && weight != workoutSet.getWeight()) { workoutSet.changeSetNumber(weight); }
      if (repetitions != null && repetitions != workoutSet.getRepetitions()) { workoutSet.changeRepetitions(repetitions); }
      if (repetitionMaximum != null && repetitionMaximum != workoutSet.getRepetitionMaximum()) { workoutSet.changeRepetitionMaximum(repetitionMaximum); }
      if (single != null && single != workoutSet.getSingle()) { workoutSet.changeIsSingle(single); }
    } else {
      workoutSet = WorkoutSet.createWorkoutSet(
          workoutExerciseId,
          single,
          weight,
          repetitions,
          repetitionMaximum,
          setNumber
      );
    }
    return workoutSetRepository.save(workoutSet);
  }

  public Boolean deleteWorkoutSet(UUID id) {
    return workoutSetRepository.deleteWorkoutSet(id);
  }
}