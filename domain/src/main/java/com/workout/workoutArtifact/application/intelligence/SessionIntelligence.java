package com.workout.workoutArtifact.application.intelligence;

import com.workout.workoutArtifact.application.session.GetSession;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.Session.UserIdsSpecification;
import com.workout.workoutArtifact.user.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionIntelligence {

  private final GetSession getSession;

  public List<SessionIntelligenceDto> getSessionIntelligence(UUID userId, Integer sessionAmount) {
    List<SessionIntelligenceDto> sessionIntelligenceDtos = new ArrayList<>();

    List<Session> sessions = getSession.execute(new UserIdsSpecification(Arrays.asList(userId)), sessionAmount);

    sessions.forEach(session -> {
      sessionIntelligenceDtos.add(new SessionIntelligenceDto(userId,session.getCreationDateTime().toLocalDate(), session.getVolume()));
    });

    return sessionIntelligenceDtos;
  }


  @Value
  public class SessionIntelligenceDto {
    private final UUID userId;
    private final LocalDate date;
    private final Double totalWeight;
  }

}