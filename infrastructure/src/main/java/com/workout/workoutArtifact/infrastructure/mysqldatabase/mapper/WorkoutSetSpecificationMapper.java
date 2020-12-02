package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> toJpaSpecification(Specification<WorkoutSet> workoutSetSpecification) {
    if (workoutSetSpecification instanceof AndSpecification) {
      return ((AndSpecification<WorkoutSet>) workoutSetSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (workoutSetSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
    throw new MappingException("Unknown specification");
  }
}
