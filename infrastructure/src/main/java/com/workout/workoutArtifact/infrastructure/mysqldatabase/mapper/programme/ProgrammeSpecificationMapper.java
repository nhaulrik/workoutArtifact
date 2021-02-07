package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme;

import com.workout.workoutArtifact.domain.programme.model.Programme;
import com.workout.workoutArtifact.domain.programme.model.Programme.PhaseIdsSpecification;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<ProgrammeEntity> toJpaSpecification(Specification<Programme> programmeSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (programmeSpecification instanceof AndSpecification) {
      return ((AndSpecification<Programme>) programmeSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (programmeSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (programmeSpecification instanceof Programme.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((Programme.IdsSpecification) programmeSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (programmeSpecification instanceof Programme.PhaseIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> {
        Root from = criteriaQuery.from(ProgrammeEntity.class);
        From join = from.join("phaseEntities");
        Path<ProgrammeEntity> p = join.get("id");
        Predicate predicate = criteriaBuilder.equal(p, ((PhaseIdsSpecification) programmeSpecification).getIds().get(0).toString());
        return predicate;
      };
    }
    throw new MappingException(String.format("Unknown specification: %s", programmeSpecification.getClass().getSimpleName()));
  }

}
