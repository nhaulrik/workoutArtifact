package com.workout.workoutArtifact.domain.user.service;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.IdsSpecification;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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


  public List<UUID> createSession(LocalDateTime parsedTime, List<UUID> userIds) {

    List<UUID> persistedSessionIds = new ArrayList<>();

    for (UUID userId : userIds) {
      Optional<User> userOptional = userRepository.getUsers(new IdsSpecification(Arrays.asList(userId))).stream().findFirst();
      if (userOptional.isPresent()) {
        User user = userOptional.get();

        Session session = Session.createNewSession(parsedTime);
        user.addSession(session);

        userRepository.save(user);
        persistedSessionIds.add(session.getId());
        log.info(String.format("user with Id: %s was updated with a new session with Id: %s", userId, session.getId().toString()));
      }
    }
    return persistedSessionIds;
  }
}
