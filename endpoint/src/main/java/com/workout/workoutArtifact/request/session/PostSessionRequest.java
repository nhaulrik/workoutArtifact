package com.workout.workoutArtifact.request.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSessionRequest {

  private final UUID id;
  private final UUID userId;
  private final String location;
  private final String programme;
  private final String splitName;
  private final String date;

  public LocalDateTime getTime() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime parsedTime = LocalDateTime.parse(date, dateTimeFormatter);
    return parsedTime;
  }
}
