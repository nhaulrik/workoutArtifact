package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExerciseRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutExerciseEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutExerciseJpaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class WorkoutExerciseEntityRepository implements WorkoutExerciseRepository {

  private final WorkoutExerciseJpaRepository workoutExerciseJpaRepository;
  private final WorkoutExerciseEntityMapper workoutExerciseEntityMapper;
  private final WorkoutExerciseSpecificationMapper workoutExerciseSpecificationMapper;

  @Transactional
  @Override
  public List<WorkoutExercise> getWorkoutExercises(Specification<WorkoutExercise> workoutExerciseSpecification) {

    org.springframework.data.jpa.domain.Specification<WorkoutExerciseEntity> jpaSpecification = workoutExerciseSpecificationMapper.toJpaSpecification(workoutExerciseSpecification);

    return workoutExerciseJpaRepository.findAll(jpaSpecification).stream()
        .map(workoutExerciseEntityMapper::toDomain)
        .filter(workoutExerciseSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public Boolean deleteWorkoutExercise(UUID id) {
    workoutExerciseJpaRepository.deleteAllById(id.toString());
    Boolean entityExists = workoutExerciseJpaRepository.existsById(id.toString());
    return !entityExists; //true when deleted successfully
  }

  @Override
  public UUID save(WorkoutExercise workoutExercise) {
    WorkoutExerciseEntity workoutExerciseEntity = workoutExerciseEntityMapper.toEntity(workoutExercise);
    return workoutExerciseJpaRepository.save(workoutExerciseEntity).getId();
  }
}
