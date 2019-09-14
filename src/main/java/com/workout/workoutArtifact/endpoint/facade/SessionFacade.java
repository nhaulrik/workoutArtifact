package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.mapper.SessionDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionFacade {

  private final SessionService sessionService;
  private final SessionMapper sessionMapper;
  private final SessionDtoSpecificationMapper sessionDtoSpecificationMapper;

  public String addSessions(List<Session> sessions) {
    return sessionService.addSessions(sessions);
  }

  public List<SessionDto> getSessions(AbstractSpecification<SessionDto> sessionDtoSpecification) {

    Specification<Session> sessionSpecification = sessionDtoSpecificationMapper.toSessionSpecification(sessionDtoSpecification);

    return sessionService.getSession(sessionSpecification).stream()
        .filter(sessionSpecification::isSatisfiedBy)
        .map(sessionMapper::toDto)
        .collect(Collectors.toList());
  }
}
