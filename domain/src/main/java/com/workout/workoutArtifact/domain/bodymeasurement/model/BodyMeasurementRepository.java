package com.workout.workoutArtifact.domain.bodymeasurement.model;

import java.util.UUID;

public interface BodyMeasurementRepository {

  BodyMeasurement getBodyMeasurement(UUID id);

  UUID save(BodyMeasurement bodyMeasurement);
}
