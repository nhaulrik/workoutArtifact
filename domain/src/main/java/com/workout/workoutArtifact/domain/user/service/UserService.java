package com.workout.workoutArtifact.domain.user.service;

import com.workout.workoutArtifact.domain.user.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

}
