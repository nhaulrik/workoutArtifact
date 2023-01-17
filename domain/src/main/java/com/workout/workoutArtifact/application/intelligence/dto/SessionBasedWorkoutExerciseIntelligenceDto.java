package com.workout.workoutArtifact.application.intelligence.dto;

import java.time.LocalDate;
import lombok.Value;

@Value
public class SessionBasedWorkoutExerciseIntelligenceDto {

  private final LocalDate date;
  private final String exerciseName;
  private final Double totalVolume;
  private final Integer totalRepetitions;
  private final Integer exerciseNumber;
  private Double averageRepetitionWeight;

  public SessionBasedWorkoutExerciseIntelligenceDto(LocalDate date, String exerciseName, Double totalVolume, Integer totalRepetitions, Integer exerciseNumber) {
    this.date = date;
    this.exerciseName = exerciseName;
    this.totalVolume = totalVolume;
    this.totalRepetitions = totalRepetitions;
    this.exerciseNumber = exerciseNumber;
    this.averageRepetitionWeight = totalVolume / totalRepetitions;
  }
}
