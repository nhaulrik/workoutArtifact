package com.workout.workoutArtifact.muscle;

import com.workout.workoutArtifact.muscle.Muscle;
import com.workout.workoutArtifact.muscle.MuscleRepository;
import com.workout.workoutArtifact.mysql.repository.MuscleJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import com.workout.workoutArtifact.mysql.mapper.MuscleEntityMapper;
import com.workout.workoutArtifact.mysql.mapper.MuscleSpecificationMapper;
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
