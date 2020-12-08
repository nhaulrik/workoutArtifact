package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.session.service.SessionService;
import com.workout.workoutArtifact.endpoint.request.CreateSessionRequest;
import com.workout.workoutArtifact.endpoint.request.CreateSessionResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SessionFacade {

  private final SessionService sessionService;

  public CreateSessionResponse createSessions(List<CreateSessionRequest> createSessionRequests) {

    List<UUID> sessionIds = new ArrayList<>();
    createSessionRequests.forEach(createSessionRequest -> {
      sessionIds.add(sessionService.createSession(createSessionRequest.getUserId(), createSessionRequest.getTime()));
    });
    return new CreateSessionResponse(sessionIds);
  }

  public Boolean deleteSession(UUID id) { return sessionService.deleteSession(id); }

}
