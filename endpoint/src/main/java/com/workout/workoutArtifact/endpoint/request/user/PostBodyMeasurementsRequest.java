package com.workout.workoutArtifact.endpoint.request.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostBodyMeasurementsRequest {

  private String dateString;
  private UUID id;
  private UUID userId;
  private Double weight;
  private Double chest;
  private Double hip;
  private Double stomach;
  private Double singleThigh;
  private Double leftThigh;
  private Double rightThigh;
  private Double singleCalve;
  private Double leftCalve;
  private Double rightCalve;
  private Double singleBiceps;
  private Double leftBiceps;
  private Double rightBiceps;
  private Double singleForearm;
  private Double leftForearm;
  private Double rightForearm;

  public LocalDate getDate() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDate parsedDate = LocalDate.parse(dateString, dateTimeFormatter);
    return parsedDate;
  }
}
