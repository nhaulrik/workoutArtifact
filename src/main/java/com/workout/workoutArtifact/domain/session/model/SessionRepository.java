package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface SessionRepository {

  List<Session> getSessions(Specification<Session> sessionSpecification);
  List<Long> addSessions(List<Session> sessions);
  Boolean deleteSessions(Specification<Session> sessionSpecification);
}
