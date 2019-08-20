package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
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
    } else if (workoutSetSpecification instanceof WorkoutSet.ExerciseNameSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("name").in(((WorkoutSet.ExerciseNameSpecification) workoutSetSpecification).getExerciseNames());
    }
    throw new MappingException("Unknown specification");
  }
}
