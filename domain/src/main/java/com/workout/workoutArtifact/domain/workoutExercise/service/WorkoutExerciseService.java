package com.workout.workoutArtifact.domain.workoutExercise.service;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.Session.IdsSpecification;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
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
  private final ExerciseRepository exerciseRepository;
  private final SessionRepository sessionRepository;

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseRepository.deleteWorkoutExercise(id);
  }

  public UUID postWorkoutExercise(UUID id, UUID exerciseId, Integer exerciseNumber, UUID sessionId) {

    Session session = sessionRepository.getSessions(new IdsSpecification(Arrays.asList(sessionId))).stream().findFirst().get();

    Optional<WorkoutExercise> workoutExerciseOptional = session.getWorkoutExercise(id);
    WorkoutExercise workoutExercise;
    if (workoutExerciseOptional.isPresent()) {
      workoutExercise = workoutExerciseOptional.get();
      if (exerciseNumber != null && exerciseNumber != workoutExercise.getExerciseNumber()) {
        workoutExercise.changeExerciseNumber(exerciseNumber);
      }
      if (!exerciseId.equals(workoutExercise.getExercise().getId())) {
        Exercise newExercise = exerciseRepository.getExercises(new ExerciseIdSpecification(exerciseId)).stream().findFirst().get();
        workoutExercise.changeExercise(newExercise);
      }
    } else {
      workoutExercise = WorkoutExercise.createWorkoutExercise(
          exerciseNumber,
          new ArrayList<>(),
          exerciseRepository.getExercises(new ExerciseIdSpecification(exerciseId)).stream().findFirst().get(),
          sessionId
      );
      session.addWorkoutExercise(workoutExercise);
    }
    sessionRepository.save(session);
    return workoutExercise.getId();
  }
}
