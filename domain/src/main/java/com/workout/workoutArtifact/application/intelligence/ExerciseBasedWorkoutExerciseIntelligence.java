package com.workout.workoutArtifact.application.intelligence;

import com.workout.workoutArtifact.application.exercise.GetExercise;
import com.workout.workoutArtifact.application.intelligence.dto.ExerciseBasedWorkoutExerciseIntelligenceDto;
import com.workout.workoutArtifact.application.intelligence.dto.ExerciseBasedWorkoutExerciseIntelligenceDto.ExerciseIntelligenceDto;
import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ExerciseBasedWorkoutExerciseIntelligence {

  private final SessionService sessionService;
  private final Map<UUID, Exercise> exerciseMap;


  public ExerciseBasedWorkoutExerciseIntelligence(SessionService sessionService, GetExercise getExercise) {
    this.sessionService = sessionService;
    this.exerciseMap =  getExercise.execute(new MatchAllSpecification()).stream()
        .collect(Collectors.toMap(Exercise::getId, exercise -> exercise));;
  }

  public List<ExerciseBasedWorkoutExerciseIntelligenceDto> getExerciseBasedWorkoutExerciseIntelligence(AbstractSpecification specification, UUID userId, Integer sessionsBack) {

    List<Session> sessions = sessionService.getLastSessions(specification, sessionsBack);
    List<ExerciseBasedWorkoutExerciseIntelligenceDto> exerciseBasedWorkoutExerciseIntelligenceDtos = new ArrayList<>();

    Map<String, List<ExerciseIntelligenceDto>> exerciseIntelligenceDtoMap = new HashMap<>();

    sessions.forEach(session -> {
      session.getWorkoutExercises().stream()
          .filter(we -> !we.getIsWarmup())
          .forEach(workoutExercise -> {
        if(exerciseIntelligenceDtoMap.containsKey(getExerciseName(workoutExercise))) {
          exerciseIntelligenceDtoMap.get(getExerciseName(workoutExercise)).add(
              new ExerciseIntelligenceDto(
                  session.getCreationDateTime().toLocalDate(),
                  workoutExercise.getTotalWorkoutExerciseWeight(),
                  workoutExercise.getTotalRepetitions(),
                  workoutExercise.getExerciseNumber(),
                  workoutExercise.getWorkoutSets().size()
              )
          );
        } else {
          exerciseIntelligenceDtoMap.put(
              getExerciseName(workoutExercise),
                  new ArrayList (
                      Arrays.asList(
                          new ExerciseIntelligenceDto(
                          session.getCreationDateTime().toLocalDate(),
                          workoutExercise.getTotalWorkoutExerciseWeight(),
                          workoutExercise.getTotalRepetitions(),
                          workoutExercise.getExerciseNumber(),
                          workoutExercise.getWorkoutSets().size()
                      )
                  )
              )
          );
        }
      });
    });

    for (Entry<String, List<ExerciseIntelligenceDto>> entry : exerciseIntelligenceDtoMap.entrySet()) {
      exerciseBasedWorkoutExerciseIntelligenceDtos.add(
          new ExerciseBasedWorkoutExerciseIntelligenceDto(
              entry.getKey(),
              entry.getValue()
          )
      );
    }
    return exerciseBasedWorkoutExerciseIntelligenceDtos.stream()
        .sorted(Comparator.comparing(ExerciseBasedWorkoutExerciseIntelligenceDto::getExerciseName))
        .collect(Collectors.toList());
  }

  private String getExerciseName(WorkoutExercise workoutExercise) {
    return exerciseMap.get(workoutExercise.getExerciseId()).getName();
  }

}
