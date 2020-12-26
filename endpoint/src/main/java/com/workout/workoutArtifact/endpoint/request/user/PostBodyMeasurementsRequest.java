package com.workout.workoutArtifact.endpoint.request.user;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostBodyMeasurementsRequest {

  private UUID id;
  private UUID userId;
  private Double weight;
  private Double chest;
  private Double hip;
  private Double stomach;
  private Double leftThigh;
  private Double rightThigh;
  private Double leftCalve;
  private Double rightCalve;
  private Double leftBiceps;
  private Double rightBiceps;
  private Double leftForearm;
  private Double rightForearm;

}
