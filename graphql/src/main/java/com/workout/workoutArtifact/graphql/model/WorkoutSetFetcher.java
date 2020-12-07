package com.workout.workoutArtifact.graphql.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.WorkoutSetDto;
import com.workout.workoutArtifact.graphql.dto.mapper.WorkoutSetDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFetcher {

  private final WorkoutSetJpaRepository workoutSetJpaRepository;
  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper;
  private final WorkoutSetDtoMapper workoutSetDtoMapper;

  public List<WorkoutSetDto> getWorkoutSet(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<WorkoutSetEntity> workoutSetEntities = workoutSetJpaRepository.findAll(jpaSpecification);
    List<WorkoutSetDto> workoutSetDtos = workoutSetEntities.stream().map(workoutSetDtoMapper::toDto).collect(Collectors.toList());

    return workoutSetDtos;
  }
}
