package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.muscle.Muscle;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class MuscleSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<MuscleEntity> toJpaSpecification(Specification<Muscle> muscleSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (muscleSpecification instanceof AndSpecification) {
      return ((AndSpecification<Muscle>) muscleSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (muscleSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (muscleSpecification instanceof Muscle.NameSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("name").in(((Muscle.NameSpecification) muscleSpecification).getNames());
    } else if (muscleSpecification instanceof Muscle.BodyPartSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("bodyPart").in(((Muscle.BodyPartSpecification) muscleSpecification).getBodyparts());
    } else if (muscleSpecification instanceof Muscle.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((Muscle.IdsSpecification) muscleSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (muscleSpecification instanceof MatchNoneSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction();
    }
    throw new MappingException("Unknown specification");
  }
}
