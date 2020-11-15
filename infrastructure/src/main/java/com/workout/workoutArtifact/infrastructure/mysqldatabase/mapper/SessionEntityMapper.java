package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionEntityMapper {

  private final WorkoutSetEntityMapper workoutSetEntityMapper;

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();

    if (session.getId() != null) {
      sessionEntity.setId(session.getId());
    }

    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setSplitName(session.getSplitName());
    sessionEntity.setProgramme(session.getProgramme());
    sessionEntity.setLocation(session.getLocation());
    sessionEntity.setWorkoutSetEntities(session.getWorkoutSet().stream().map(workoutSetEntityMapper::toEntity).collect(Collectors.toSet()));
    return sessionEntity;
  }

  public List<SessionEntity> toEntity(List<Session> sessions) {
    return sessions.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

  public Session toDomainObject(SessionEntity sessionEntity) {
    Session session = new Session(sessionEntity.getId(), sessionEntity.getCreationDateTime());
    session.setProgramme(session.getProgramme());
    session.setSplitName(session.getSplitName());
    session.setLocation(session.getLocation());
    session.setUserId(sessionEntity.getUserEntity().getId());
    session.getWorkoutSet().addAll(sessionEntity.getWorkoutSetEntities().stream().map(workoutSetEntityMapper::toDomain).collect(Collectors.toList()));

    return session;
  }
}
