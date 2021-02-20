package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.dto.BodyMeasurementDto;
import com.workout.workoutArtifact.dto.mapper.BodyMeasurementDtoMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.mysql.entity.BodyMeasurementEntity;
import com.workout.workoutArtifact.mysql.mapper.BodyMeasurementSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.BodyMeasurementJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BodyMeasurementFetcher {

  private final BodyMeasurementJpaRepository bodyMeasurementJpaRepository;
  private final BodyMeasurementDtoMapper bodyMeasurementDtoMapper;
  private final BodyMeasurementSpecificationMapper bodyMeasurementSpecificationMapper;

  public List<BodyMeasurementDto> getBodyMeasurements(AbstractSpecification aggregatedSpecification) {

    org.springframework.data.jpa.domain.Specification jpaSpecification = bodyMeasurementSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<BodyMeasurementEntity> allByUserEntity = bodyMeasurementJpaRepository.findAll(jpaSpecification);

    return allByUserEntity.stream()
        .map(bodyMeasurementDtoMapper::toDto)
        .collect(Collectors.toList());
  }

}
