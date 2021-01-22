package com.workout.workoutArtifact.graphql.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class BodyMeasurementDto {

  @NonNull
  private UUID id;
  @NonNull
  private UUID userId;
  @NonNull
  private LocalDate date;
  private Double weight;
  private Double chest;
  private Double hip;
  private Double waist;
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
