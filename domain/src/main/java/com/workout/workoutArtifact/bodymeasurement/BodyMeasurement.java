package com.workout.workoutArtifact.bodymeasurement;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;

@Getter
public class BodyMeasurement {

  private UUID id;
  private UUID userId;

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

  private BodyMeasurement(UUID id, UUID userId, LocalDate date, Double weight, Double chest, Double hip, Double waist, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    this.id = id;
    this.userId = userId;
    this.date = date;
    this.weight = weight;
    this.chest = chest;
    this.hip = hip;
    this.waist = waist;
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

  public static BodyMeasurement instantiate(UUID id, UUID userId, LocalDate date, Double weight, Double chest, Double hip, Double waist, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    return new BodyMeasurement(
        id,
        userId,
        date,
        weight,
        chest,
        hip,
        waist,
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

  public static BodyMeasurement createNewBodyMeasurement(UUID userId, LocalDate date, Double weight, Double chest, Double hip, Double waist, Double stomach, Double leftThigh, Double rightThigh, Double leftCalve, Double rightCalve, Double leftBiceps, Double rightBiceps, Double leftForearm, Double rightForearm) {
    return new BodyMeasurement(
        UUID.randomUUID(),
        userId,
        date,
        weight,
        chest,
        hip,
        waist,
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

  @Value
  public static class UserIdsSpecification extends AbstractSpecification<BodyMeasurement> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(BodyMeasurement bodyMeasurement) {
      return true;
    }
  }

}
