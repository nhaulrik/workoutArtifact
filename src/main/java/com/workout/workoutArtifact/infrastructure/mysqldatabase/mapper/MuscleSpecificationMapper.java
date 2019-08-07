package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
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
    }
    throw new MappingException("Unknown specification");
  }
}
