package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.UserFacade;
import com.workout.workoutArtifact.endpoint.request.session.PostSessionRequest;
import com.workout.workoutArtifact.endpoint.request.session.PostSessionResponse;
import com.workout.workoutArtifact.endpoint.request.user.PostUserRequest;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserFacade userFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public UUID postUser(@RequestBody PostUserRequest postUserRequest) {
    return userFacade.postUser(postUserRequest);
  }


}
