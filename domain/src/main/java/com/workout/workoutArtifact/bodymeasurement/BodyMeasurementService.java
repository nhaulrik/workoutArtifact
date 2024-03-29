package com.workout.workoutArtifact.bodymeasurement;

import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BodyMeasurementService {

  private final BodyMeasurementRepository bodyMeasurementRepository;

  public UUID postBodyMeasurements(
      UUID id,
      UUID userId,
      LocalDate createdDate,
      Double weight,
      Double chest,
      Double hip,
      Double waist,
      Double stomach,
      Double leftThigh,
      Double rightThigh,
      Double leftCalve,
      Double rightCalve,
      Double leftBiceps,
      Double rightBiceps,
      Double leftForearm,
      Double rightForearm
  ) {

    BodyMeasurement bodyMeasurement;
    if (id != null) {
      bodyMeasurement = bodyMeasurementRepository.getBodyMeasurement(id);

    } else {
      bodyMeasurement = BodyMeasurement.createNewBodyMeasurement(
          userId,
          createdDate,
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
    return bodyMeasurementRepository.save(bodyMeasurement);
  }
}
