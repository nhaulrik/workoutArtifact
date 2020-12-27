package com.workout.workoutArtifact.graphql.fetcher;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.BodyMeasurementDto;
import com.workout.workoutArtifact.graphql.dto.mapper.BodyMeasurementDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.BodyMeasurementEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.BodyMeasurementSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.BodyMeasurementJpaRepository;
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
