package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.bodymeasurement.model.BodyMeasurement;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.BodyMeasurementEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class BodyMeasurementSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<BodyMeasurementEntity> toJpaSpecification(Specification<BodyMeasurement> bodyMeasurementSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (bodyMeasurementSpecification instanceof AndSpecification) {
      return ((AndSpecification<BodyMeasurement>) bodyMeasurementSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (bodyMeasurementSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (bodyMeasurementSpecification instanceof BodyMeasurement.UserIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("userEntity").get("id").in(((BodyMeasurement.UserIdsSpecification) bodyMeasurementSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    }
    throw new MappingException(String.format("Unknown specification: %s", bodyMeasurementSpecification.getClass().getSimpleName()));
  }

}
