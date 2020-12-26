package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final UserService userService;

}
