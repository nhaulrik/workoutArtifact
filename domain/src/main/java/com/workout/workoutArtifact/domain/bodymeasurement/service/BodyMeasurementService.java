package com.workout.workoutArtifact.domain.bodymeasurement.service;

import com.workout.workoutArtifact.domain.bodymeasurement.model.BodyMeasurement;
import com.workout.workoutArtifact.domain.bodymeasurement.model.BodyMeasurementRepository;
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
      Double weight,
      Double chest,
      Double hip,
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
    return bodyMeasurementRepository.save(bodyMeasurement);
  }
}
