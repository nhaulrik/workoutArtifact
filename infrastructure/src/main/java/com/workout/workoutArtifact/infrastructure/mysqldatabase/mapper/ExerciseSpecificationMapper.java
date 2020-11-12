package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
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
    } else if (exerciseSpecification instanceof Exercise.BodyPartsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("bodyPart").in(((Exercise.BodyPartsSpecification) exerciseSpecification).getBodyParts());
    } else if (exerciseSpecification instanceof Exercise.ExerciseIdSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((Exercise.ExerciseIdSpecification) exerciseSpecification).getId());
    }
    throw new MappingException(String.format("Unknown specification: %s", exerciseSpecification.getClass().getSimpleName()));
  }

}
