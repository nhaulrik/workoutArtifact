package com.workout.workoutArtifact.application.intelligence;

import com.workout.workoutArtifact.application.intelligence.ExerciseIntelligence.ExerciseAverage;
import com.workout.workoutArtifact.exercise.ExerciseService;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.SpecificationBuilder;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseIntelligence {

  private final ExerciseService exerciseService;
  private final SessionService sessionService;

  public ExerciseIntelligence getAverages(UUID userId, Integer sessionsBack, List<UUID> exerciseIds) {

    AbstractSpecification specification = new SpecificationBuilder()
        .withSpecification(new Session.UserIdsSpecification(Arrays.asList(userId)))
        .build();

    List<Session> sessions = sessionService.getLastSessions(specification, sessionsBack);

    Map<UUID, String> exerciseMap = exerciseService.getExerciseMap(exerciseIds);

    List<ExerciseAverage> exerciseAverages = new ArrayList<>();

    for (UUID exerciseId : exerciseIds) {

      AtomicReference<Integer> repetitions = new AtomicReference<>(0);
      AtomicReference<Double> totalWeight = new AtomicReference<Double>(0d);
      AtomicReference<Integer> setCount = new AtomicReference<>(0);

      sessions.stream()
          .map(Session::getWorkoutExercises)
          .flatMap(Collection::stream)
          .filter(we -> exerciseId.equals(we.getExerciseId()))
          .map(WorkoutExercise::getWorkoutSets)
          .flatMap(Collection::stream)
          .forEach(ws -> {
            repetitions.updateAndGet(v -> v + ws.getRepetitions());
            totalWeight.updateAndGet(v -> v + (ws.getWeight() * ws.getRepetitions()));
            setCount.updateAndGet(v -> v + 1);
          });

      exerciseAverages.add(new ExerciseAverage(exerciseMap.get(exerciseId), totalWeight.get()/repetitions.get(), setCount.get()));
    }
    return new ExerciseIntelligence(userId, exerciseAverages);
  }

}
