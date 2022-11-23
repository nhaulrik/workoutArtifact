package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.dto.SessionDto;
import com.workout.workoutArtifact.dto.mapper.SessionDtoMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import com.workout.workoutArtifact.mysql.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.SessionJpaRepository;
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
