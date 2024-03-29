package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.bodymeasurement.BodyMeasurement;
import com.workout.workoutArtifact.mysql.entity.BodyMeasurementEntity;
import com.workout.workoutArtifact.mysql.entity.UserEntity;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BodyMeasurementEntityMapper {

  private final EntityManager entityManager;

  public BodyMeasurement toDomain(BodyMeasurementEntity bodyMeasurementEntity) {

    BodyMeasurement bodyMeasurement = BodyMeasurement.instantiate(
        bodyMeasurementEntity.getId(),
        bodyMeasurementEntity.getUserEntity().getId(),
        bodyMeasurementEntity.getDate(),
        bodyMeasurementEntity.getWeight(),
        bodyMeasurementEntity.getChest(),
        bodyMeasurementEntity.getHip(),
        bodyMeasurementEntity.getWaist(),
        bodyMeasurementEntity.getStomach(),
        bodyMeasurementEntity.getLeftThigh(),
        bodyMeasurementEntity.getRightThigh(),
        bodyMeasurementEntity.getLeftCalve(),
        bodyMeasurementEntity.getRightCalve(),
        bodyMeasurementEntity.getLeftBiceps(),
        bodyMeasurementEntity.getRightBiceps(),
        bodyMeasurementEntity.getLeftForearm(),
        bodyMeasurementEntity.getRightForearm()
    );
    return bodyMeasurement;
  }

  public BodyMeasurementEntity toEntity(BodyMeasurement bodyMeasurement) {

    BodyMeasurementEntity bodyMeasurementEntity = new BodyMeasurementEntity(
        bodyMeasurement.getId().toString(),
        null,
        null,
        bodyMeasurement.getDate(),
        bodyMeasurement.getWeight(),
        bodyMeasurement.getChest(),
        bodyMeasurement.getHip(),
        bodyMeasurement.getWaist(),
        bodyMeasurement.getStomach(),
        bodyMeasurement.getLeftThigh(),
        bodyMeasurement.getRightThigh(),
        bodyMeasurement.getLeftCalve(),
        bodyMeasurement.getRightCalve(),
        bodyMeasurement.getLeftBiceps(),
        bodyMeasurement.getRightBiceps(),
        bodyMeasurement.getLeftForearm(),
        bodyMeasurement.getRightForearm(),
        entityManager.getReference(UserEntity.class, bodyMeasurement.getUserId().toString())
    );
    return bodyMeasurementEntity;
  }

}
