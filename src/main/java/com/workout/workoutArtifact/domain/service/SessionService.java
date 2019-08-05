package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.mapper.SessionMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.SessionRepository;
import com.workout.workoutArtifact.domain.model.Session;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionService {

  private final SessionRepository sessionRepository;
  private final SessionMapper sessionMapper;

  public String addSession(Session session) {
    SessionEntity sessionEntity = sessionMapper.toEntity(session);
    sessionRepository.save(sessionEntity);
    return "1 session added";
  }

  public String addSession(List<Session> sessions) {
    sessions.forEach(this::addSession);
    return sessions.size() + " sessions added by list";
  }

}
