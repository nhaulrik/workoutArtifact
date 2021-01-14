package com.workout.workoutArtifact.graphql.intelligence.dto;

import io.leangen.graphql.annotations.types.GraphQLType;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@GraphQLType
public class ExerciseIntelligenceDto {

  private UUID userId;
  private List<ExerciseAverage> exerciseAverages;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class ExerciseAverage {

    private String exerciseName;
    private Double exerciseAverage;
    private Integer setCount;
  }

}
