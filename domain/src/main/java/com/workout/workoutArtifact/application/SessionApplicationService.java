package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.user.User;
import com.workout.workoutArtifact.user.User.IdsSpecification;
import com.workout.workoutArtifact.user.UserService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionApplicationService {

  private final UserService userService;
  private final SessionService sessionService;

  public UUID handleSessionRequest(UUID id, UUID userId, LocalDateTime time, String location, String programme, String splitName) {

    Optional<User> userOptional = userService.getUsers(new IdsSpecification(Arrays.asList(userId))).stream().findFirst();

    if (userOptional.isPresent()) {
      if (id != null) {
        return sessionService.postSession(id, location, programme, splitName);
      } else {
        return sessionService.createSession(userId, time, location, programme, splitName);
      }
    }
    throw new RuntimeException(String.format("user with id %s was not found", userId));
  }


}
