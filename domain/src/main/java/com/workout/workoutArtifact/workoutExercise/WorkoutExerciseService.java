package com.workout.workoutArtifact.workoutExercise;

import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.exercise.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.exercise.ExerciseRepository;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.Session.IdsSpecification;
import com.workout.workoutArtifact.session.SessionRepository;
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

  public UUID postWorkoutExercise(UUID id, UUID exerciseId, Integer exerciseNumber, UUID sessionId, Boolean isWarmup) {

    Session session = sessionRepository.getSessions(new IdsSpecification(Arrays.asList(sessionId))).stream().findFirst().get();

    Optional<WorkoutExercise> workoutExerciseOptional = session.getWorkoutExercise(id);
    WorkoutExercise workoutExercise;
    if (workoutExerciseOptional.isPresent()) {
      workoutExercise = workoutExerciseOptional.get();
      if (exerciseNumber != null && exerciseNumber != workoutExercise.getExerciseNumber()) {
        workoutExercise.changeExerciseNumber(exerciseNumber);
        workoutExercise.changeIsWarmup(isWarmup);
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
          isWarmup,
          sessionId
      );
      session.addWorkoutExercise(workoutExercise);
    }
    sessionRepository.save(session);
    return workoutExercise.getId();
  }
}
