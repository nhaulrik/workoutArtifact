package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseEntityRepository implements ExerciseRepository {

  private final ExerciseMapper exerciseMapper;
  private final ExerciseJpaRepository exerciseJpaRepository;
  private final ExerciseSpecificationMapper exerciseSpecificationMapper;

  @Override
  public List<Exercise> getExercises(Specification<Exercise> exerciseSpecification) {

    org.springframework.data.jpa.domain.Specification<ExerciseEntity> jpaSpecification = exerciseSpecificationMapper.toJpaSpecification(exerciseSpecification);

    return exerciseJpaRepository.findAll(jpaSpecification).stream()
        .map(exerciseMapper::toDomainObject)
        .filter(exerciseSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public String addExercises(List<Exercise> exercises) {
    return exercises.stream()
        .map(exerciseMapper::toEntity)
        .map(exerciseJpaRepository::save)
        .map(entity -> entity.getName())
        .collect(Collectors.toList())
        .toString();
  }
}
