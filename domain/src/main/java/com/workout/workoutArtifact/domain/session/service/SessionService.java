package com.workout.workoutArtifact.domain.session.service;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.IdsSpecification;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import com.workout.workoutArtifact.domain.user.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final UserRepository userRepository;

  public UUID postSession(UUID id, UUID userId, LocalDateTime time, String location, String programme, String splitName) {

    Optional<User> userOptional = userRepository.getUsers(new IdsSpecification(Arrays.asList(userId))).stream().findFirst();

    if (userOptional.isPresent()) {
      User user = userOptional.get();

      Optional<Session> sessionOptional = user.getSession(id);

      Session session;
      if (sessionOptional.isPresent()) {
        session = sessionOptional.get();

        if (location != null && location != session.getLocation()) { session.changeLocation(location); }
        if (programme != null && programme != session.getProgramme()) { session.changeProgramme(programme); }
        if (splitName != null && splitName != session.getSplitName()) { session.changeSplitName(splitName); }

      } else {
        session = Session.createNewSession(
            time,
            location,
            programme,
            splitName
        );
        user.addSession(session);
      }
      userRepository.save(user);
      return session.getId();
    }
    throw new RuntimeException(String.format("could not post session with userId: %s", userId));
  }

  public UUID save(Session session) {
    return sessionRepository.save(session);
  }

  public Boolean deleteSession(UUID id) {
    return sessionRepository.deleteSessions(new Session.IdsSpecification(Arrays.asList(id)));
  }
}
