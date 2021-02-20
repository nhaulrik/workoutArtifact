package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class WorkoutExerciseSpecificationMapper {

  public org.springframework.data.jpa.domain.Specification<WorkoutExerciseEntity> toJpaSpecification(Specification<WorkoutExercise> workoutExerciseSpecification) {
    if (workoutExerciseSpecification instanceof AndSpecification) {
      return ((AndSpecification<WorkoutExercise>) workoutExerciseSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (workoutExerciseSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (workoutExerciseSpecification instanceof WorkoutExercise.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((WorkoutExercise.IdsSpecification) workoutExerciseSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (workoutExerciseSpecification instanceof WorkoutExercise.ExerciseNumbersSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("exerciseNumber").in(((WorkoutExercise.ExerciseNumbersSpecification) workoutExerciseSpecification).getExerciseNumbers());
    } else if (workoutExerciseSpecification instanceof WorkoutExercise.SessionIdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("sessionEntity").get("id").in(((WorkoutExercise.SessionIdsSpecification) workoutExerciseSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    }
    throw new MappingException("Unknown specification");
  }

}
