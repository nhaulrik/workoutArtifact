package com.workout.workoutArtifact.bodymeasurement;

import com.workout.workoutArtifact.bodymeasurement.BodyMeasurement;
import com.workout.workoutArtifact.bodymeasurement.BodyMeasurementRepository;
import com.workout.workoutArtifact.mysql.entity.BodyMeasurementEntity;
import com.workout.workoutArtifact.mysql.mapper.BodyMeasurementEntityMapper;
import com.workout.workoutArtifact.mysql.repository.BodyMeasurementJpaRepository;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BodyMeasurementEntityRepository implements BodyMeasurementRepository {

  private final BodyMeasurementJpaRepository bodyMeasurementJpaRepository;
  private final BodyMeasurementEntityMapper bodyMeasurementEntityMapper;

  public UUID save(BodyMeasurement bodyMeasurement) {
    BodyMeasurementEntity bodyMeasurementEntity = bodyMeasurementEntityMapper.toEntity(bodyMeasurement);
    return bodyMeasurementJpaRepository.save(bodyMeasurementEntity).getId();
  }

  @Override
  public BodyMeasurement getBodyMeasurement(UUID id) {
    BodyMeasurementEntity bodyMeasurementEntity = bodyMeasurementJpaRepository.findAllById(Arrays.asList(id.toString()))
        .stream()
        .findFirst()
        .get();

    return bodyMeasurementEntityMapper.toDomain(bodyMeasurementEntity);
  }
}
