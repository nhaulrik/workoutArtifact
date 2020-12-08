package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.OrSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
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
    } else if (sessionSpecification instanceof OrSpecification) {
      return ((OrSpecification<Session>) sessionSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::or)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (sessionSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (sessionSpecification instanceof Session.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((Session.IdsSpecification) sessionSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (sessionSpecification instanceof Session.LocationsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("location").in(((Session.LocationsSpecification) sessionSpecification).getLocations());
    } else if (sessionSpecification instanceof Session.ProgrammeSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("programme").in(((Session.ProgrammeSpecification) sessionSpecification).getProgramme());
    } else if (sessionSpecification instanceof Session.UserIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("userEntity").get("id").in(((Session.UserIdsSpecification) sessionSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (sessionSpecification instanceof Session.DateTimeSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        LocalDateTime parsedLocalDateTime = ((Session.DateTimeSpecification) sessionSpecification).getLocalDateTime();
        return criteriaBuilder.between(root.get("creationDateTime"), parsedLocalDateTime, parsedLocalDateTime.plusDays(1));
      };
    }
    throw new MappingException("Unknown specification");
  }

}
