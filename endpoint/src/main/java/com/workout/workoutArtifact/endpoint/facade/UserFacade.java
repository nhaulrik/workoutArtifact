package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.application.WorkoutApplicationService;
import com.workout.workoutArtifact.domain.user.service.UserService;
import com.workout.workoutArtifact.endpoint.request.user.PostUserRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final WorkoutApplicationService workoutApplicationService;

  public UUID postUser(PostUserRequest postUserRequest) {
    return workoutApplicationService.handleUserRequest(
        postUserRequest.getId(),
        postUserRequest.getFirstName(),
        postUserRequest.getLastName(),
        postUserRequest.getBirthday(),
        postUserRequest.getGender()
    );

  }
}
