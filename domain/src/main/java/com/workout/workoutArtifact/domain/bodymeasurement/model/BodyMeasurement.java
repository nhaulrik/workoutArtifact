package com.workout.workoutArtifact.domain.bodymeasurement.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class BodyMeasurement {

  private UUID id;
  private UUID userId;

  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

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

  private BodyMeasurement(UUID id, UUID userId, LocalDateTime createDate, LocalDateTime modifyDate, Double weight, Double chest, Double hip, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    this.id = id;
    this.userId = userId;
    this.createDate = createDate;
    this.modifyDate = modifyDate;
    this.weight = weight;
    this.chest = chest;
    this.hip = hip;
    this.stomach = stomach;
    this.leftThigh = leftThigh;
    this.rightThigh = rightThigh;
    this.leftCalve = leftCalve;
    this.rightCalve = rightCalve;
    this.leftBiceps = leftBiceps;
    this.rightBiceps = rightBiceps;
    this.leftForearm = leftForearm;
    this.rightForearm = rightForearm;
  }

  public static BodyMeasurement instantiate(UUID id, UUID userId, LocalDateTime createDate, LocalDateTime modifyDate, Double weight, Double chest, Double hip, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    return new BodyMeasurement(
        id,
        userId,
        createDate,
        modifyDate,
        weight,
        chest,
        hip,
        stomach,
        leftThigh,
        rightThigh,
        leftCalve,
        rightCalve,
        leftBiceps,
        rightBiceps,
        leftForearm,
        rightForearm
    );
  }

  public static BodyMeasurement createNewBodyMeasurement(UUID userId, Double weight, Double chest, Double hip, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    return new BodyMeasurement(
        UUID.randomUUID(),
        userId,
        LocalDateTime.now(),
        LocalDateTime.now(),
        weight,
        chest,
        hip,
        stomach,
        leftThigh,
        rightThigh,
        leftCalve,
        rightCalve,
        leftBiceps,
        rightBiceps,
        leftForearm,
        rightForearm
    );
  }

}
