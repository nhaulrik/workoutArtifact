package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class ExerciseRelationSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<ExerciseRelationEntity> toJpaSpecification(Specification<ExerciseRelation> exerciseRelationSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (exerciseRelationSpecification instanceof AndSpecification) {
      return ((AndSpecification<ExerciseRelation>) exerciseRelationSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (exerciseRelationSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (exerciseRelationSpecification instanceof ExerciseRelation.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("compositeIdEntity").get("exerciseId").in(((ExerciseRelation.IdsSpecification) exerciseRelationSpecification).getExerciseIds());
    }
    throw new MappingException("Unknown specification");
  }


}
