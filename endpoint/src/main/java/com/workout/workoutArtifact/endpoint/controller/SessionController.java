package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.endpoint.request.CreateSessionRequest;
import com.workout.workoutArtifact.endpoint.request.CreateSessionResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

  private final SessionFacade sessionFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public CreateSessionResponse createSessions(@RequestBody List<CreateSessionRequest> createSessionRequests) {
    return sessionFacade.createSessions(createSessionRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteSession(UUID id) {
    return sessionFacade.deleteSession(id);
  }

}
