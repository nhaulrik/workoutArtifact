package com.workout.workoutArtifact.facade;

import com.workout.workoutArtifact.application.SessionApplicationService;
import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.request.session.PostSessionRequest;
import com.workout.workoutArtifact.request.session.PostSessionResponse;
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

  private final SessionApplicationService sessionApplicationService;
  private final SessionService sessionService;

  public PostSessionResponse postSessions(List<PostSessionRequest> postSessionRequests) {

    List<UUID> sessionIds = new ArrayList<>();
    postSessionRequests.forEach(postSessionRequest -> {
      sessionIds.add(sessionApplicationService.handleSessionRequest(
          postSessionRequest.getId(),
          postSessionRequest.getUserId(),
          postSessionRequest.getTime(),
          postSessionRequest.getLocation(),
          postSessionRequest.getProgramme(),
          postSessionRequest.getSplitName()
      ));
    });
    return new PostSessionResponse(sessionIds);
  }

  public Boolean deleteSession(UUID id) {
    return sessionService.deleteSession(id);
  }

}
