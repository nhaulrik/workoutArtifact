package com.workout.workoutArtifact.bodymeasurement;

import java.util.UUID;

interface BodyMeasurementRepository {

  BodyMeasurement getBodyMeasurement(UUID id);

  UUID save(BodyMeasurement bodyMeasurement);
}
