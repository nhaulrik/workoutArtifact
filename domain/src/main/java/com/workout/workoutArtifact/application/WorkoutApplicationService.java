package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.session.SessionService;
import com.workout.workoutArtifact.user.User;
import com.workout.workoutArtifact.user.User.IdsSpecification;
import com.workout.workoutArtifact.user.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutApplicationService {

  private final SessionService sessionService;
  private final UserService userService;

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


  public UUID handleUserRequest(UUID id, String firstName, String lastName, LocalDate birthday, String gender) {

    if (id != null) {
      return userService.postUser(id, firstName, lastName, birthday, gender);
    } else {
      return userService.createUser(firstName, lastName, birthday, gender);
    }
  }
}
