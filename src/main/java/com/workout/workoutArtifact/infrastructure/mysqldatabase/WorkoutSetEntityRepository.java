package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class WorkoutSetEntityRepository implements WorkoutSetRepository {

  private final WorkoutSetJpaRepository workoutSetJpaRepository;
  private final WorkoutSetMapper workoutSetMapper;
  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper;

  @Transactional
  @Override
  public List<WorkoutSet> getWorkoutSet(Specification<WorkoutSet> workoutSetSpecification) {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(workoutSetSpecification);

    return workoutSetJpaRepository.findAll(jpaSpecification).stream()
        .map(workoutSetMapper::toDomain)
        .filter(workoutSetSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public List<Long> addWorkoutSet(List<WorkoutSet> workoutSets) {
    List<Long> workoutSetIds = new ArrayList<>();
    workoutSetIds.addAll(workoutSetJpaRepository.saveAll(
        workoutSets.stream()
            .map(workoutSetMapper::toEntity)
            .collect(Collectors.toList())
    ).stream()
    .map(entity -> entity.getId()).collect(Collectors.toList()));
    return workoutSetIds;
  }
}
