package com.workout.workoutArtifact.workoutset;

import com.workout.workoutArtifact.mysql.mapper.WorkoutSetSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.mysql.mapper.WorkoutSetEntityMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class WorkoutSetEntityRepository implements WorkoutSetRepository {

  private final WorkoutSetJpaRepository workoutSetJpaRepository;
  private final WorkoutSetEntityMapper workoutSetEntityMapper;
  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper;

  @Transactional
  @Override
  public List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification) {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(workoutSetSpecification);

    return workoutSetJpaRepository.findAll(jpaSpecification).stream()
        .map(workoutSetEntityMapper::toDomain)
        .filter(workoutSetSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public List<UUID> addWorkoutSet(List<WorkoutSet> workoutSets) {
    List<UUID> workoutSetIds = new ArrayList<>();
    workoutSetIds.addAll(workoutSetJpaRepository.saveAll(
        workoutSets.stream()
            .map(workoutSetEntityMapper::toEntity)
            .collect(Collectors.toList())
    ).stream()
        .map(entity -> entity.getId()).collect(Collectors.toList()));
    return workoutSetIds;
  }

  @Transactional
  @Override
  public Boolean deleteWorkoutSet(UUID id) {
    workoutSetJpaRepository.deleteAllById(id.toString());
    return ! workoutSetJpaRepository.existsById(id.toString());
  }

  @Override
  public UUID save(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = workoutSetEntityMapper.toEntity(workoutSet);
    return workoutSetJpaRepository.save(workoutSetEntity).getId();
  }
}
