package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.IdsSpecification;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExerciseRepository;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
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
  private final UserRepository userRepository;

  public UUID postWorkoutSet(UUID id, UUID userId, UUID sessionId, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID workoutExerciseId) {

    User user = userRepository.getUsers(new IdsSpecification(Arrays.asList(userId))).stream().findFirst().get();
    Session session = user.getSession(sessionId).get();
    WorkoutExercise workoutExercise = session.getWorkoutExercise(workoutExerciseId).get();

    Optional<WorkoutSet> workoutSetOptional = workoutExercise.getWorkoutSet(id);

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
      workoutExercise.addWorkoutSet(workoutSet);
    }
    return userRepository.save(user);
  }

  public Boolean deleteWorkoutSet(UUID id) {
    return workoutSetRepository.deleteWorkoutSet(id);
  }
}
