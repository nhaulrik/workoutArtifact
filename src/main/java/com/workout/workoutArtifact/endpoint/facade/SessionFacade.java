package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.model.Session;
import com.workout.workoutArtifact.domain.service.SessionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionFacade {

  private final SessionService sessionService;

  public String addSessions(List<Session> sessions) {
    return sessionService.addSession(sessions);
  }

}
