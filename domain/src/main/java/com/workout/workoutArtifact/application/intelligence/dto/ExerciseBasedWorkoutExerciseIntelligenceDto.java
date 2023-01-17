package com.workout.workoutArtifact.application.intelligence.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Value;

@Value
public class ExerciseBasedWorkoutExerciseIntelligenceDto {

  private final String exerciseName;
  private List<ExerciseIntelligenceDto> exerciseIntelligenceDtos;

  @Value
  public static class ExerciseIntelligenceDto {
    private final LocalDate date;
    private final Double totalVolume;
    private final Integer totalRepetitions;
    private final Integer exerciseNumber;
    private final Integer setCount;
    private Double averageRepetitionWeight;

    public ExerciseIntelligenceDto(LocalDate date, Double totalVolume, Integer totalRepetitions, Integer exerciseNumber, Integer setCount) {
      this.date = date;
      this.totalVolume = totalVolume;
      this.totalRepetitions = totalRepetitions;
      this.exerciseNumber = exerciseNumber;
      this.averageRepetitionWeight = totalVolume / totalRepetitions;
      this.setCount = setCount;
    }
  }

}

