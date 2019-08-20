package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface SessionRepository {

  List<Session> getSessions(Specification<Session> sessionSpecification);
  String addSessions(List<Session> sessions);
}
