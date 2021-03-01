package com.workout.workoutArtifact.session;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;

interface SessionRepository {

  List<Session> getSessions(Specification<Session> sessionSpecification);
  List<UUID> addSessions(List<Session> sessions);
  Boolean deleteSessions(Specification<Session> sessionSpecification);

  UUID save(Session session);

  List<Session> getLastSessions(AbstractSpecification abstractSpecification, Integer amount);
}
