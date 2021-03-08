package com.workout.workoutArtifact.application.session;

import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionRepository;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSession {

  private final SessionRepository sessionRepository;

  public List<Session> execute(AbstractSpecification<Session> abstractSpecification, Integer lastSessions) {
    return sessionRepository.getLastSessions(abstractSpecification, lastSessions);
  }
}
