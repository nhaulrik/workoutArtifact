package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetService {

  private final WorkoutSetRepository workoutSetRepository;
  private final ExerciseRepository exerciseRepository;
  private final SessionRepository sessionRepository;

  public List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification) {
    return workoutSetRepository.getWorkoutSet(workoutSetSpecification);
  }

  public UUID addWorkoutSet(WorkoutSet workoutSet) {
    UUID workoutSetId = workoutSetRepository.addWorkoutSet(Arrays.asList(workoutSet)).get(0);
    return workoutSetId;
  }

  public void postWorkoutSet(UUID id, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID exerciseId, UUID sessionId) {

    if (sessionId != null) {
      Optional<Session> sessionOptional = sessionRepository.getSessions(new Session.IdsSpecification(Arrays.asList(sessionId))).stream().findFirst();

      if (sessionOptional.isPresent()) {
        Session session = sessionOptional.get();

        Optional<WorkoutSet> workoutSetOptional = session.getWorkoutSet(id);
        WorkoutSet workoutSet;
        if (workoutSetOptional.isPresent()) {
          workoutSet = workoutSetOptional.get();

          if (setNumber != null && setNumber != workoutSet.getSetNumber()) { workoutSet.changeSetNumber(setNumber); }
          if (weight != null && weight != workoutSet.getWeight()) { workoutSet.changeSetNumber(weight); }
          if (repetitions != null && repetitions != workoutSet.getRepetitions()) { workoutSet.changeRepetitions(repetitions); }
          if (repetitionMaximum != null && repetitionMaximum != workoutSet.getRepetitionMaximum()) { workoutSet.changeRepetitionMaximum(repetitionMaximum); }
          if (single != null && single != workoutSet.getSingle()) { workoutSet.changeIsSingle(single); }
          if (sessionId != null && sessionId != workoutSet.getSessionId()) { workoutSet.changeSession(sessionId); }
          if (exerciseId != null && exerciseId != workoutSet.getExercise().getId()) {
            Optional<Exercise> newExerciseOptional = exerciseRepository.getExercises(new ExerciseIdSpecification(exerciseId)).stream().findFirst();
            if (newExerciseOptional.isPresent()) {
              workoutSet.changeExercise(newExerciseOptional.get());
            }
          }
        } else {
          workoutSet = new WorkoutSet(
              UUID.randomUUID(),
              sessionId,
              exerciseRepository.getExercises(new Exercise.ExerciseIdSpecification(exerciseId)).stream().findFirst().get(),
              single,
              weight,
              repetitions,
              repetitionMaximum,
              setNumber
          );
        }
        workoutSetRepository.addWorkoutSet(Arrays.asList(workoutSet));
      }
    }
  }
}
