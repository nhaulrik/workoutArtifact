package com.workout.workoutArtifact.domain.workoutset.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
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

  public UUID postWorkoutSet(UUID id, Integer setNumber, Double weight, Integer repetitions, Integer repetitionMaximum, Boolean single, UUID workoutExerciseId, UUID sessionId) {

    if (sessionId != null) {
      Optional<Session> sessionOptional = sessionRepository.getSessions(new Session.IdsSpecification(Arrays.asList(sessionId))).stream().findFirst();

      if (sessionOptional.isPresent()) {
        Session session = sessionOptional.get();

        Optional<WorkoutExercise> workoutExerciseOptional = session.getWorkoutExercise(workoutExerciseId);

        if (workoutExerciseOptional.isPresent()) {
          WorkoutExercise workoutExercise = workoutExerciseOptional.get();
          Optional<WorkoutSet> workoutSetOptional = workoutExercise.getWorkoutSet(id);

          if (workoutSetOptional.isPresent()) {
            WorkoutSet workoutSet = workoutSetOptional.get();
            if (setNumber != null && setNumber != workoutSet.getSetNumber()) { workoutSet.changeSetNumber(setNumber); }
            if (weight != null && weight != workoutSet.getWeight()) { workoutSet.changeSetNumber(weight); }
            if (repetitions != null && repetitions != workoutSet.getRepetitions()) { workoutSet.changeRepetitions(repetitions); }
            if (repetitionMaximum != null && repetitionMaximum != workoutSet.getRepetitionMaximum()) { workoutSet.changeRepetitionMaximum(repetitionMaximum); }
            if (single != null && single != workoutSet.getSingle()) { workoutSet.changeIsSingle(single); }

            sessionRepository.addSessions(Arrays.asList(session)).stream().findFirst().get();
            return workoutSet.getId();
          } else {
            WorkoutSet newWorkoutSet = WorkoutSet.createWorkoutSet(
                workoutExerciseId,
                single,
                weight,
                repetitions,
                repetitionMaximum,
                setNumber
            );
            workoutExercise.getWorkoutSets().add(newWorkoutSet);
            sessionRepository.addSessions(Arrays.asList(session)).stream().findFirst().get();
            return newWorkoutSet.getId();
          }
        }
      }
    }
    throw new RuntimeException("something went wrong when posting workoutSet");
  }

  public void deleteWorkoutSet(UUID id) {
    workoutSetRepository.deleteWorkoutSet(id);
  }
}
