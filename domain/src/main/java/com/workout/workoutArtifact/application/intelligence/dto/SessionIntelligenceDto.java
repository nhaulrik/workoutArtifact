package com.workout.workoutArtifact.application.intelligence.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionIntelligenceDto {

  private LocalDateTime dateTime;
  private UUID userId;
  private UUID sessionId;
  private Integer count;
  private Integer totalRepetitions;
  private Double totalWeight;
}
