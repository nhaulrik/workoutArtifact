package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.application.service.ApplicationService;
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
public class ApplicationFacade {

  private final ApplicationService applicationService;

  public CreateSessionResponse createSessions(List<CreateSessionRequest> createSessionRequests) {

    List<UUID> sessionIds = new ArrayList<>();
    createSessionRequests.forEach(createSessionRequest -> sessionIds.add(applicationService.createSession(createSessionRequest.getUserId(), createSessionRequest.getTime())));
    return new CreateSessionResponse(sessionIds);
  }

}
