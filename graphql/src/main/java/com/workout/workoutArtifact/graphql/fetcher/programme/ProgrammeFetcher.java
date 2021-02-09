package com.workout.workoutArtifact.graphql.fetcher.programme;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.PhaseDto;
import com.workout.workoutArtifact.graphql.dto.ProgrammeDto;
import com.workout.workoutArtifact.graphql.dto.mapper.PhaseDtoMapper;
import com.workout.workoutArtifact.graphql.dto.mapper.ProgrammeDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.programme.ProgrammeSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ProgrammeJpaRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProgrammeFetcher {

  private final ProgrammeJpaRepository programmeJpaRepository;
  private final ProgrammeSpecificationMapper programmeSpecificationMapper;
  private final ProgrammeDtoMapper programmeDtoMapper;
  private final PhaseDtoMapper phaseDtoMapper;

  public List<ProgrammeDto> getProgrammes(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = programmeSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<ProgrammeEntity> programmeEntities = programmeJpaRepository.findAll(jpaSpecification);
    List<ProgrammeDto> programmeDtos = programmeEntities.stream().map(programmeDtoMapper::toDto).collect(Collectors.toList());

    return programmeDtos;
  }

  @Transactional
  public List<PhaseDto> getPhases(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = programmeSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<ProgrammeEntity> programmeEntities = programmeJpaRepository.findAll(jpaSpecification);

    return programmeEntities.stream()
        .map(ProgrammeEntity::getPhaseEntities)
        .flatMap(Collection::stream)
        .map(phaseDtoMapper::toDto)
        .collect(Collectors.toList());
  }
}
