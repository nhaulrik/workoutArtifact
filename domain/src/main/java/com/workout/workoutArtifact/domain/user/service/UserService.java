package com.workout.workoutArtifact.domain.user.service;

import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.IdsSpecification;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import java.util.List;
import java.util.Optional;
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

//  public List<UUID> createSession(LocalDateTime parsedTime, List<UUID> userIds) {
//
//    List<UUID> persistedSessionIds = new ArrayList<>();
//
//    for (UUID userId : userIds) {
//      Optional<User> userOptional = userRepository.getUsers(new IdsSpecification(Arrays.asList(userId))).stream().findFirst();
//      if (userOptional.isPresent()) {
//        User user = userOptional.get();
//
//        if (user.getSessionForDate(parsedTime).isPresent()) {
//          throw new RuntimeException(String.format("There is already a session for userId: %s on date: %s", userId, parsedTime.toLocalDate().toString()));
//        }
//
//        Session session = Session.createNewSession(userId, parsedTime);
//        user.addSession(session);
//
//        userRepository.save(user);
//        persistedSessionIds.add(session.getId());
//        log.info(String.format("user with Id: %s was updated with a new session with Id: %s", userId, session.getId().toString()));
//      }
//    }
//    return persistedSessionIds;
//  }

  public Optional<User> getUser(IdsSpecification idsSpecification) {
    return userRepository.getUsers(idsSpecification).stream().findFirst();
  }

  public void save(User user) {
    userRepository.save(user);
  }
}
