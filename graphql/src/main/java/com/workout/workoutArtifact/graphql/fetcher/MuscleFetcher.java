package com.workout.workoutArtifact.graphql.fetcher;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.MuscleDto;
import com.workout.workoutArtifact.graphql.dto.mapper.MuscleDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuscleFetcher {

  private final MuscleJpaRepository muscleJpaRepository;
  private final MuscleSpecificationMapper muscleSpecificationMapper;
  private final MuscleDtoMapper muscleDtoMapper;

  public List<MuscleDto> getMuscles(AbstractSpecification aggregatedSpecification) {
    Specification jpaSpecification = muscleSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<MuscleEntity> muscleEntities = muscleJpaRepository.findAll(jpaSpecification);
    List<MuscleDto> muscleDtos = muscleEntities.stream().map(muscleDtoMapper::toDto).collect(Collectors.toList());

    return muscleDtos;
  }
}
