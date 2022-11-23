package com.workout.workoutArtifact.facade;

import com.workout.workoutArtifact.application.UserApplicationService;
import com.workout.workoutArtifact.request.user.PostUserRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final UserApplicationService userApplicationService;

  public UUID postUser(PostUserRequest postUserRequest) {
    return userApplicationService.handleUserRequest(
        postUserRequest.getId(),
        postUserRequest.getFirstName(),
        postUserRequest.getLastName(),
        postUserRequest.getBirthday(),
        postUserRequest.getGender()
    );

  }
}
