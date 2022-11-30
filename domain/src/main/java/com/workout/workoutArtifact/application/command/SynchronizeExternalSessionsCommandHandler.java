package com.workout.workoutArtifact.application.command;

import com.workout.workoutArtifact.session.ExternalSessionRepository;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.Session.DateTimeSpecification;
import com.workout.workoutArtifact.session.SessionRepository;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SynchronizeExternalSessionsCommandHandler {

  private final SessionRepository sessionRepository;
  private final ExternalSessionRepository externalSessionRepository;

  public SynchronizeExternalSessionsCommandHandler(
      SessionRepository sessionRepository,
      ExternalSessionRepository externalSessionRepository) {
    this.sessionRepository = sessionRepository;
    this.externalSessionRepository = externalSessionRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void synchronize() {
    List<Session> externalSessions = externalSessionRepository.getExternalSessions(UUID.randomUUID());

    externalSessions.forEach(externalSession -> {
      List<Session> existingSessions = sessionRepository.getSessions(
          new DateTimeSpecification(externalSession.getCreationDateTime()));
      if (existingSessions.size() > 1) {
        log.warn("too many sessions found. unable to match external session");
        return;
      } else if (existingSessions.isEmpty()) {
        log.warn("no session found matching external session");
        return;
      }

      Session existingSession = existingSessions.get(0);
      existingSession.setCalories(externalSession.getCalories());
      existingSession.setDuration(externalSession.getDuration());
      existingSession.setDuration(externalSession.getDuration());
      existingSession.setSport(externalSession.getSport());
      existingSession.setPolarId(externalSession.getPolarId());
      existingSession.setHeartRateAverage(externalSession.getHeartRateAverage());
      existingSession.setHeartRateMaximum(externalSession.getHeartRateMaximum());

      sessionRepository.save(existingSession);
      Boolean bla = false;
    });

    Boolean bla = false;
  }

}
