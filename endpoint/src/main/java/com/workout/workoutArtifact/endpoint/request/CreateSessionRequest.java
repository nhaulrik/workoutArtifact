package com.workout.workoutArtifact.endpoint.request;

import com.workout.workoutArtifact.endpoint.request.util.Time;
import java.util.UUID;
import lombok.Data;

@Data
public class CreateSessionRequest extends Time {

  private final UUID userId;

  public CreateSessionRequest(UUID userId, String time) {
    super(time);
    this.userId = userId;
  }
}
