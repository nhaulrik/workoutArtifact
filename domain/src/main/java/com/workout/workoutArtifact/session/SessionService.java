package com.workout.workoutArtifact.session;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;

  public List<Session> getSessions(Specification<Session> sessionSpecification) {
    return sessionRepository.getSessions(sessionSpecification);
  }

  public UUID createSession(UUID userId, LocalDateTime time, String location, String programme, String splitName) {

    Session session = Session.createNewSession(
        time,
        location,
        programme,
        splitName,
        userId
    );
    return sessionRepository.save(session);
  }


  public UUID postSession(UUID id, String location, String programme, String splitName) {

    Optional<Session> sessionOptional = sessionRepository.getSessions(new Session.IdsSpecification(Arrays.asList(id))).stream().findFirst();

    Session session;
    if (sessionOptional.isPresent()) {
      session = sessionOptional.get();

      if (location != null && location != session.getLocation()) {
        session.changeLocation(location);
      }
      if (programme != null && programme != session.getProgramme()) {
        session.changeProgramme(programme);
      }
      if (splitName != null && splitName != session.getSplitName()) {
        session.changeSplitName(splitName);
      }

      return sessionRepository.save(session);
    }
    throw new RuntimeException(String.format("session with id %s was not found", id));
  }

  public UUID save(Session session) {
    return sessionRepository.save(session);
  }

  public Boolean deleteSession(UUID id) {
    return sessionRepository.deleteSessions(new Session.IdsSpecification(Arrays.asList(id)));
  }

  public List<Session> getLastSessions(AbstractSpecification abstractSpecification, Integer amount) {

    return sessionRepository.getLastSessions(abstractSpecification, amount);

  }
}
