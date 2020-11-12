package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseEntityRepository implements ExerciseRepository {

  private final ExerciseEntityMapper exerciseEntityMapper;
  private final ExerciseJpaRepository exerciseJpaRepository;
  private final ExerciseSpecificationMapper exerciseSpecificationMapper;

  @Override
  public List<Exercise> getExercises(Specification<Exercise> exerciseSpecification) {

    org.springframework.data.jpa.domain.Specification<ExerciseEntity> jpaSpecification = exerciseSpecificationMapper.toJpaSpecification(exerciseSpecification);

    return exerciseJpaRepository.findAll(jpaSpecification).stream()
        .map(exerciseEntityMapper::toDomainObject)
        .filter(exerciseSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public String addExercises(List<Exercise> exercises) {
    return exercises.stream()
        .map(exerciseEntityMapper::toEntity)
        .map(exerciseJpaRepository::save)
        .map(entity -> entity.getName())
        .collect(Collectors.toList())
        .toString();
  }
}
