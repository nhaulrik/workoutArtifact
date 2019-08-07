package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.MuscleRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuscleEntityRepository implements MuscleRepository {

  private final MuscleMapper muscleMapper;
  private final MuscleJpaRepository muscleJpaRepository;
  private final MuscleSpecificationMapper muscleSpecificationMapper;

  @Override
  public List<Muscle> getMuscles(Specification<Muscle> muscleSpecification) {

    org.springframework.data.jpa.domain.Specification<MuscleEntity> jpaSpecification = muscleSpecificationMapper.toJpaSpecification(muscleSpecification);

   return muscleJpaRepository.findAll(jpaSpecification).stream()
    .map(muscleMapper::toDomainObject)
    .filter(muscleSpecification::isSatisfiedBy)
    .collect(Collectors.toList());
  }

  @Override
  public String addMuscles(List<Muscle> muscles) {
    return muscles.stream()
        .map(muscleMapper::toEntity)
        .map(muscleJpaRepository::save)
        .map(entity -> entity.getName())
        .collect(Collectors.toList())
        .toString();
  }

}
