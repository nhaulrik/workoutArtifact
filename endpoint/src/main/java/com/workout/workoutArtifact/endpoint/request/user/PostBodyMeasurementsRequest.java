package com.workout.workoutArtifact.endpoint.request.user;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostBodyMeasurementsRequest {

  private UUID userId;
  private double weight;
  private double forearm;
  private double biceps;
  private double calves;
  private double chest;
  private double hips;
  private double stomach;
  private double thigh;

}
