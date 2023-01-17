package com.workout.workoutArtifact.exercise;

import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.exercise.ExerciseRepository;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.mapper.ExerciseEntityMapper;
import com.workout.workoutArtifact.mysql.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.ExerciseJpaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseEntityRepository implements ExerciseRepository {

  private final ExerciseJpaRepository exerciseJpaRepository;
  private final ExerciseSpecificationMapper exerciseSpecificationMapper;
  private final ExerciseEntityMapper exerciseEntityMapper;

  @Override
  @Transactional
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

  @Override
  public UUID save(Exercise exercise) {
    ExerciseEntity exerciseEntity = exerciseEntityMapper.toEntity(exercise);
    return exerciseJpaRepository.save(exerciseEntity).getId();
  }

  @Transactional
  @Override
  public Boolean delete(UUID id) {
    exerciseJpaRepository.deleteAllById(id.toString());
    return exerciseJpaRepository.findById(id.toString()) == null;
  }
}
