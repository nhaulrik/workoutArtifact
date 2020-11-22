package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetSpecificationMapper {
  public org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> toJpaSpecification(Specification<WorkoutSet> workoutSetSpecification) {
// TODO: 08-08-2019 add alll abstract specifiacation
    if (workoutSetSpecification instanceof AndSpecification) {
      return ((AndSpecification<WorkoutSet>) workoutSetSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (workoutSetSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (workoutSetSpecification instanceof WorkoutSet.ExerciseIdSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("exerciseId").in(((WorkoutSet.ExerciseIdSpecification) workoutSetSpecification).getExerciseIds());
    } else if (workoutSetSpecification instanceof WorkoutSet.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((WorkoutSet.IdsSpecification) workoutSetSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (workoutSetSpecification instanceof WorkoutSet.SessionIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("sessionEntity").get("id").in(((WorkoutSet.SessionIdsSpecification) workoutSetSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    }

    throw new MappingException("Unknown specification");
  }
}
