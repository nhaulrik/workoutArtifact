package com.workout.workoutArtifact.domain.session.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.Session.IdsSpecification;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final ExerciseRepository exerciseRepository;

  public List<UUID> addSessions(List<Session> sessions) {
    List<UUID> sessionIds = sessionRepository.addSessions(sessions);
    return sessionIds;
  }

  public List<Session> getSession(Specification<Session> sessionSpecification) {
    List<Session> sessions = sessionRepository.getSessions(sessionSpecification);
    return sessions;
  }

  public Boolean deleteSessions(Specification<Session> sessionSpecification) {
    return sessionRepository.deleteSessions(sessionSpecification);
  }

  public UUID postWorkoutExercise(UUID id, Integer exerciseNumber, UUID sessionId, UUID exerciseId) {
    Optional<Session> sessionOptional = sessionRepository.getSessions(new IdsSpecification(Arrays.asList(sessionId))).stream().findFirst();
    if (sessionOptional.isPresent()) {
      Session session = sessionOptional.get();

      UUID workoutExerciseId = null;
      WorkoutExercise workoutExercise;
      if (id != null) {
        Optional<WorkoutExercise> workoutExerciseOptional = session.getWorkoutExercise(id);
        if (workoutExerciseOptional.isPresent()) {
          workoutExercise = workoutExerciseOptional.get();
          workoutExercise.updateExerciseNumber(exerciseNumber);
          workoutExerciseId = workoutExercise.getId();
        }
      } else {
        Exercise exercise = exerciseRepository.getExercises(new Exercise.ExerciseIdSpecification(exerciseId)).stream().findFirst().get();

        workoutExercise = WorkoutExercise.createWorkoutExercise(sessionId, exerciseNumber, new ArrayList<>(), exercise);
        workoutExerciseId = workoutExercise.getId();
        session.addWorkoutExercise(workoutExercise);
      }
      sessionRepository.addSessions(Arrays.asList(session)).stream().findFirst().get();
      return workoutExerciseId;
    }
    throw new RuntimeException(String.format("session with id: %s was not found", sessionId));
  }
}
