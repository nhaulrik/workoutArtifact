package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.BodyMeasurementDto;
import com.workout.workoutArtifact.mysql.entity.BodyMeasurementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class BodyMeasurementDtoMapper {

  public BodyMeasurementDto toDto(BodyMeasurementEntity bodyMeasurementEntity) {

    return new BodyMeasurementDto(
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

  }

}
