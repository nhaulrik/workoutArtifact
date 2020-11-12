package com.workout.workoutArtifact.domain.session.service;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;

  public List<UUID> addSessions(List<Session> sessions) {
    List<UUID> sessionIds = sessionRepository.addSessions(sessions);
    return sessionIds;
  }

  public List<Session> getSession(Specification<Session> sessionSpecification) {
    List<Session> sessions = sessionRepository.getSessions(sessionSpecification);
    return sessions;
  }

  public Boolean deleteSessions(Specification<Session> sessionSpecification) {
    return sessionRepository.deleteSessions(sessionSpecification);
  }
}
