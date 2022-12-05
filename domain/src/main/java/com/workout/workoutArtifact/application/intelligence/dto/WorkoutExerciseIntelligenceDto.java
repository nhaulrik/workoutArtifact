package com.workout.workoutArtifact.application.intelligence.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Value;

@Value
public class WorkoutExerciseIntelligenceDto {

  private final UUID userId;
  private final LocalDate date;
  private final String exerciseName;

}
