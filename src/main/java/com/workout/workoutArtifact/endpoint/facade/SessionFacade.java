package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionFacade {

  private final SessionService sessionService;

  public String addSessions(List<Session> sessions) {
    return sessionService.addSessions(sessions);
  }

}
