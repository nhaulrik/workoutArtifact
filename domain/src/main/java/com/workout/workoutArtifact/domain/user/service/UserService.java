package com.workout.workoutArtifact.domain.user.service;

import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public String addUser(User user) {
    return userRepository.addUser(user);
  }

  public List<User> getUsers(Specification<User> userSpecification) {
    return userRepository.getUsers(userSpecification);
  }

}
