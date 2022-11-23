package com.workout.workoutArtifact.application;

import com.workout.workoutArtifact.user.UserService;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserApplicationService {

  private final UserService userService;

  public UUID handleUserRequest(UUID id, String firstName, String lastName, LocalDate birthday, String gender) {

    if (id != null) {
      return userService.postUser(id, firstName, lastName, birthday, gender);
    } else {
      return userService.createUser(firstName, lastName, birthday, gender);
    }
  }

}
