package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.workoutset.WorkoutSet;
import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import java.util.UUID;
import java.util.stream.Collectors;
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
    } else if (workoutSetSpecification instanceof WorkoutSet.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((WorkoutSet.IdsSpecification) workoutSetSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (workoutSetSpecification instanceof WorkoutSet.WorkoutExerciseIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("workoutExerciseEntity").get("id").in(((WorkoutSet.WorkoutExerciseIdsSpecification) workoutSetSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    }
    throw new MappingException("Unknown specification");
  }
}
