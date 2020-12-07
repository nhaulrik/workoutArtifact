package com.workout.workoutArtifact.graphql.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDto {

  private UUID id;
  private String location;
  private String programme;
  private String splitName;
  private LocalDateTime localDateTime;

}
