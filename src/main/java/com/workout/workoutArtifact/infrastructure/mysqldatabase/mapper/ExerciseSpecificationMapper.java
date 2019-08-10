package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class ExerciseSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<ExerciseEntity> toJpaSpecification(Specification<Exercise> exerciseSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (exerciseSpecification instanceof AndSpecification) {
      return ((AndSpecification<Exercise>) exerciseSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (exerciseSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (exerciseSpecification instanceof Exercise.NameSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("name").in(((Exercise.NameSpecification) exerciseSpecification).getNames());
    }
    throw new MappingException("Unknown specification");
  }

}
