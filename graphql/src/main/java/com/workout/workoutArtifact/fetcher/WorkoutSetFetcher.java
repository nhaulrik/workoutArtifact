package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.dto.WorkoutSetDto;
import com.workout.workoutArtifact.dto.mapper.WorkoutSetDtoMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.mysql.mapper.WorkoutSetSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.WorkoutSetJpaRepository;
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
