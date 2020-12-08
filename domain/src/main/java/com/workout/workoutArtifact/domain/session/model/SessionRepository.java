package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface SessionRepository {

  List<Session> getSessions(Specification<Session> sessionSpecification);
  List<UUID> addSessions(List<Session> sessions);
  Boolean deleteSessions(Specification<Session> sessionSpecification);

  UUID createSession(Session session);
}
