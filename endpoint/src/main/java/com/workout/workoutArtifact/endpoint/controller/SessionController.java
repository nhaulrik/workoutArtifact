package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.endpoint.request.session.PostSessionRequest;
import com.workout.workoutArtifact.endpoint.request.session.PostSessionResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

  private final SessionFacade sessionFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostSessionResponse postSessions(@RequestBody List<PostSessionRequest> postSessionRequests) {
    return sessionFacade.postSessions(postSessionRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteSession(@PathVariable UUID id) {
    return sessionFacade.deleteSession(id);
  }

}
