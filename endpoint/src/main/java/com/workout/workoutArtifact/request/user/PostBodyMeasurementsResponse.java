package com.workout.workoutArtifact.request.user;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class PostBodyMeasurementsResponse {

  private final List<UUID> postedBodyMeasurementsResponse;

}
