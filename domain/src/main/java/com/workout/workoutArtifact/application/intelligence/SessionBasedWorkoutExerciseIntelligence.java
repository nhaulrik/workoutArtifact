package com.workout.workoutArtifact.application.intelligence;

import com.workout.workoutArtifact.application.exercise.GetExercise;
import com.workout.workoutArtifact.application.intelligence.dto.ExerciseIntelligenceDto.BodyDistribution;
import com.workout.workoutArtifact.application.intelligence.dto.ExerciseIntelligenceDto.ExerciseAverage;
import com.workout.workoutArtifact.application.intelligence.dto.SessionBasedWorkoutExerciseIntelligenceDto;
import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.exercise.Exercise.ExerciseIdsSpecification;
import com.workout.workoutArtifact.exercise.ExerciseService;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionBasedWorkoutExerciseIntelligence {

  private final ExerciseService exerciseService;
  private final SessionService sessionService;
  private final GetExercise getExercise;

  public List<SessionBasedWorkoutExerciseIntelligenceDto> getSessionBasedIntelligence(AbstractSpecification specification, UUID userId, Integer sessionsBack) {

    List<Session> sessions = sessionService.getLastSessions(specification, sessionsBack);
    List<SessionBasedWorkoutExerciseIntelligenceDto> sessionBasedWorkoutExerciseIntelligenceDtos = new ArrayList<>();

    List<UUID> exerciseIds = sessions.stream().map(Session::getWorkoutExercises).flatMap(Collection::stream).map(WorkoutExercise::getExerciseId).distinct().collect(Collectors.toList());
    Map<UUID, Exercise> exerciseMap = new HashMap<>();
    getExercise.execute(new ExerciseIdsSpecification(exerciseIds)).forEach(exercise -> exerciseMap.put(exercise.getId(), exercise));


    sessions.forEach(session -> {
      session.getWorkoutExercises().stream()
          .filter(we -> !we.getIsWarmup())
          .forEach(workoutExercise -> {
            sessionBasedWorkoutExerciseIntelligenceDtos.add(
                new SessionBasedWorkoutExerciseIntelligenceDto(
                    session.getCreationDateTime().toLocalDate(),
                    exerciseMap.get(workoutExercise.getExerciseId()).getName(),
                    workoutExercise.getTotalWorkoutExerciseWeight(),
                    workoutExercise.getTotalRepetitions(),
                    workoutExercise.getExerciseNumber()
                )
        );
      });
    });

    return sessionBasedWorkoutExerciseIntelligenceDtos.stream()
        .sorted(Comparator.comparing(SessionBasedWorkoutExerciseIntelligenceDto::getDate).reversed()
            .thenComparing((SessionBasedWorkoutExerciseIntelligenceDto::getExerciseNumber)))
        .collect(Collectors.toList());
  }

  public List<BodyDistribution> getBodyIntelligence(List<Session> sessions) {

    List<UUID> exerciseIds = sessions.stream().map(Session::getWorkoutExercises).flatMap(Collection::stream).map(WorkoutExercise::getExerciseId).distinct().collect(Collectors.toList());
    Map<UUID, Exercise> exerciseMap = new HashMap<>();
    getExercise.execute(new ExerciseIdsSpecification(exerciseIds)).forEach(exercise -> exerciseMap.put(exercise.getId(), exercise));

    List<WorkoutExercise> allWorkoutExercises = sessions.stream().map(Session::getWorkoutExercises).flatMap(Collection::stream).collect(Collectors.toList());

    Map<String, Double> bodyPartWeightMap = new HashMap<>();

    for (WorkoutExercise workoutExercise : allWorkoutExercises) {
      Double workoutExerciseWeight = workoutExercise.getWorkoutSets().stream().map(ws -> ws.getTotalWeight()).reduce(0d, Double::sum);

      if (bodyPartWeightMap.containsKey(exerciseMap.get(workoutExercise.getExerciseId()).getBodyPart())) {
        Double totalWeight = bodyPartWeightMap.get(exerciseMap.get(workoutExercise.getExerciseId()).getBodyPart());
        totalWeight += workoutExerciseWeight;
        bodyPartWeightMap.put(exerciseMap.get(workoutExercise.getExerciseId()).getBodyPart(), totalWeight);
      } else {
        bodyPartWeightMap.put(exerciseMap.get(workoutExercise.getExerciseId()).getBodyPart(), workoutExerciseWeight);
      }

    }
    List<BodyDistribution> bodyDistributions = new ArrayList<>();
    bodyPartWeightMap.forEach((key, value) -> bodyDistributions.add(new BodyDistribution(key, value, null)));
    return bodyDistributions.stream()
        .sorted(Comparator.comparingDouble(BodyDistribution::getTotalVolume))
        .collect(Collectors.toList());
  }

  public List<ExerciseAverage> getAverages(List<Session> sessions) {
    List<UUID> exerciseIds = sessions.stream().map(Session::getWorkoutExercises).flatMap(Collection::stream).map(WorkoutExercise::getExerciseId).distinct().collect(Collectors.toList());

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

      exerciseAverages.add(new ExerciseAverage(exerciseMap.get(exerciseId), totalWeight.get() / repetitions.get(), setCount.get(), totalWeight.get(), repetitions.get()));
    }
    exerciseAverages = exerciseAverages.stream().sorted(Comparator.comparingInt(ExerciseAverage::getSetCount).reversed()).collect(Collectors.toList());
    if (exerciseAverages.size() > 9) {
      exerciseAverages = exerciseAverages.subList(0, 9);
    }
    return exerciseAverages;
  }

}
