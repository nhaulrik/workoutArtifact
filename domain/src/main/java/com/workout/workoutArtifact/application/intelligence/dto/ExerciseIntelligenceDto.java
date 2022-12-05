package com.workout.workoutArtifact.application.intelligence.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class ExerciseIntelligenceDto {

  private final UUID userId;
  private final List<ExerciseAverage> exerciseAverages;
  private final List<BodyDistribution> bodyDistributions;

  public ExerciseIntelligenceDto(UUID userId, List<ExerciseAverage> exerciseAverages, List<BodyDistribution> bodyDistributions) {
    this.userId = userId;
    this.exerciseAverages = exerciseAverages;
    this.bodyDistributions = bodyDistributions;

    Double totalWeight = this.bodyDistributions.stream().map(BodyDistribution::getTotalVolume).reduce(0d, Double::sum);
    this.bodyDistributions.forEach(bodyDistribution -> bodyDistribution.updatePercentage(totalWeight));
  }

  @Value
  @AllArgsConstructor
  public static class ExerciseAverage {

    private String exerciseName;
    private Double exerciseAverage;
    private Integer setCount;
    private Double volume;
    private Integer repetitions;

  }

  @RequiredArgsConstructor
  @Getter
  public static class BodyDistribution {

    private final String bodyPart;
    private Double percentage;
    private final Double totalVolume;
    private final Double frequency;

    public void updatePercentage(Double totalWeight) {
      this.percentage = (totalVolume/totalWeight)*100;
    }
  }
}
