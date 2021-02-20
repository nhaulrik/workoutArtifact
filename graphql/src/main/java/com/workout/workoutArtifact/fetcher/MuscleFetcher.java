package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.dto.MuscleDto;
import com.workout.workoutArtifact.dto.mapper.MuscleDtoMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import com.workout.workoutArtifact.mysql.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.MuscleJpaRepository;
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
