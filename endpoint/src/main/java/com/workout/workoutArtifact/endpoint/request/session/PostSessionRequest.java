package com.workout.workoutArtifact.endpoint.request.session;

import com.workout.workoutArtifact.endpoint.request.util.Time;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSessionRequest extends Time {

  private final UUID id;
  private final UUID userId;
  private final String location;
  private final String programme;
  private final String splitName;

}
