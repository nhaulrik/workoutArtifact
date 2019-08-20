package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class SessionSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<SessionEntity> toJpaSpecification(
      Specification<Session> sessionSpecification) {
    // TODO: 08-08-2019 add alll abstract specifiacation
    if (sessionSpecification instanceof AndSpecification) {
      return ((AndSpecification<Session>) sessionSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (sessionSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
    throw new MappingException("Unknown specification");
  }

}
