package com.workout.workoutArtifact.application.intelligence;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Value;

@Value
public class SessionIntelligenceDto {
  private final UUID userId;
  private final LocalDate date;
  private final Double totalWeight;
}
