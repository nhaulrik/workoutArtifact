package com.workout.workoutArtifact.domain.workoutExercise.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExerciseRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseService {

  private final WorkoutExerciseRepository workoutExerciseRepository;
  private final SessionRepository sessionRepository;
  private final ExerciseRepository exerciseRepository;

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseRepository.deleteWorkoutExercise(id);
  }

  public UUID createWorkoutExercise(UUID id, UUID exerciseId, Integer exerciseNumber, UUID sessionId) {

    Optional<Session> sessionOptional = sessionRepository.getSessions(new Session.IdsSpecification(Arrays.asList(sessionId))).stream().findFirst();

    if (sessionOptional.isPresent()) {
      Session session = sessionOptional.get();

      WorkoutExercise workoutExercise = WorkoutExercise.createWorkoutExercise(
          exerciseNumber,
          new ArrayList<>(),
          exerciseRepository.getExercises(new ExerciseIdSpecification(exerciseId)).stream().findFirst().get()
      );

      session.getWorkoutExercises().add(workoutExercise);

      sessionRepository.save(session);
      return workoutExercise.getId();
    }
    return null;
  }
}
