package com.workout.workoutArtifact.graphql.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.graphql.dto.mapper.SessionDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionFetcher {

  private final SessionJpaRepository sessionJpaRepository;
  private final SessionSpecificationMapper sessionSpecificationMapper;
  private final SessionDtoMapper sessionDtoMapper;

  public List<SessionDto> getSessions(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = sessionSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<SessionEntity> sessionEntities = sessionJpaRepository.findAll(jpaSpecification);
    List<SessionDto> sessionDtos = sessionEntities.stream().map(sessionDtoMapper::toDto).collect(Collectors.toList());

    return sessionDtos;
  }
}
