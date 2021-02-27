package com.workout.workoutArtifact.application.intelligence;


import com.workout.workoutArtifact.exercise.ExerciseService;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionIntelligence {

  private final SessionService sessionService;
  private final ExerciseService exerciseService;

  public Map<String, Double> getAverages(UUID userId, List<UUID> exerciseIds) {

    List<Session> sessions = sessionService.getSessions(new Session.UserIdsSpecification(Arrays.asList(userId)));

    Map<UUID, List<Double>> averagesMap = new HashMap<>();

    List<WorkoutExercise> workoutExercises = sessions.stream()
        .map(Session::getWorkoutExercises)
        .flatMap(Collection::stream)
        .filter(we -> exerciseIds.contains(we.getExerciseId()))
        .collect(Collectors.toList());

    workoutExercises.forEach(we -> averagesMap.put(we.getExerciseId(), new ArrayList<>()));
    workoutExercises.forEach(we -> averagesMap.get(we.getExerciseId()).add(we.getAverage()));

    Map<UUID, String> exerciseMap = exerciseService.getExerciseMap(workoutExercises.stream().map(WorkoutExercise::getExerciseId).collect(Collectors.toList()));

    Map<String, Double> resultMap = new HashMap<>();
    averagesMap.entrySet().forEach(e -> resultMap.put(
        exerciseMap.get(e.getKey()),
        e.getValue().stream().mapToDouble(d -> d).average().getAsDouble()));

    return resultMap;
  }


}
