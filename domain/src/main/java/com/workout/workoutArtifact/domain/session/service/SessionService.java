package com.workout.workoutArtifact.domain.session.service;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.IdsSpecification;
import com.workout.workoutArtifact.domain.user.service.UserService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final UserService userService;

  public UUID createSession(UUID userId, LocalDate time) {

    Optional<User> userOptional = userService.getUser(new IdsSpecification(Arrays.asList(userId)));

    if (userOptional.isPresent()) {
      User user = userOptional.get();

      Session newSession = Session.createNewSession(time.atStartOfDay());
      user.addSession(newSession);

      userService.save(user);
      return newSession.getId();
    }
    throw new RuntimeException(String.format("could not create session with userId: %s and time: %s", userId, time.toString()));
  }

  public UUID save(Session session) {
    return sessionRepository.save(session);
  }

  public Boolean deleteSession(UUID id) {
    return sessionRepository.deleteSessions(new Session.IdsSpecification(Arrays.asList(id)));
  }
}
