package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.endpoint.mapper.dto.SessionDtoMapper;
import com.workout.workoutArtifact.endpoint.mapper.specification.SessionDtoSpecificationMapper;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionFacade {

  private final SessionService sessionService;
  private final SessionDtoMapper sessionDtoMapper;
  private final SessionDtoSpecificationMapper sessionDtoSpecificationMapper;

  public List<UUID> addSessions(List<SessionDto> sessionDtos) {
    List<UUID> sessionIds = sessionService.addSessions(sessionDtos.stream()
    .map(sessionDtoMapper::toDomainObject)
    .collect(Collectors.toList()));

    return sessionIds;
  }

  public List<SessionDto> getSessions(AbstractSpecification<SessionDto> sessionDtoSpecification) {

    Specification<Session> sessionSpecification = sessionDtoSpecificationMapper.toSessionSpecification(sessionDtoSpecification);

    return sessionService.getSession(sessionSpecification).stream()
        .filter(sessionSpecification::isSatisfiedBy)
        .map(sessionDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public Boolean deleteSessions(AbstractSpecification sessionIdSpecification) {

    Specification<Session> sessionSpecification = sessionDtoSpecificationMapper.toSessionSpecification(sessionIdSpecification);

    return sessionService.deleteSessions(sessionSpecification);
  }
}
