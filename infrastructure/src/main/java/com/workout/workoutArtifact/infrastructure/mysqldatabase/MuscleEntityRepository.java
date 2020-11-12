package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.MuscleRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuscleEntityRepository implements MuscleRepository {

  private final MuscleEntityMapper muscleEntityMapper;
  private final MuscleJpaRepository muscleJpaRepository;
  private final MuscleSpecificationMapper muscleSpecificationMapper;

  @Override
  public List<Muscle> getMuscles(Specification<Muscle> muscleSpecification) {

    org.springframework.data.jpa.domain.Specification<MuscleEntity> jpaSpecification = muscleSpecificationMapper.toJpaSpecification(muscleSpecification);

    return muscleJpaRepository.findAll(jpaSpecification).stream()
        .map(muscleEntityMapper::toDomainObject)
        .filter(muscleSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public String addMuscles(List<Muscle> muscles) {
    return muscles.stream()
        .map(muscleEntityMapper::toEntity)
        .map(muscleJpaRepository::save)
        .map(entity -> entity.getName())
        .collect(Collectors.toList())
        .toString();
  }

}
