package com.workout.workoutArtifact.domain.session.service;

import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;

  public String addSessions(List<Session> sessions) {
    return sessionRepository.addSessions(sessions);
  }

  public List<Session> getSession(Specification<Session> sessionSpecification) {
    return sessionRepository.getSessions(sessionSpecification);
  }
}
