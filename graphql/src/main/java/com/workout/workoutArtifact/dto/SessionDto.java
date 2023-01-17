package com.workout.workoutArtifact.dto;

import com.workout.workoutArtifact.session.Sport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDto {

  private UUID id;
  private String location;
  private String programme;
  private String splitName;
  private LocalDateTime localDateTime;
  private UUID userId;
  private Long durationMinutes;
  private Integer calories;
  private Sport sport;
  private Integer heartRateAverage;
  private Integer heartRateMaximum;

}
