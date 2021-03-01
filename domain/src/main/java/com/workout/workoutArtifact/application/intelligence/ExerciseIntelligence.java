package com.workout.workoutArtifact.application.intelligence;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ExerciseIntelligence {

  private UUID userId;
  private List<ExerciseAverage> exerciseAverages;

  @Value
  @AllArgsConstructor
  public static class ExerciseAverage {

    private String exerciseName;
    private Double exerciseAverage;
    private Integer setCount;
  }

}
