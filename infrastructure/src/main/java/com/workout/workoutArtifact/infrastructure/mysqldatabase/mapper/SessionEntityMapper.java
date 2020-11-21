package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionEntityMapper {

  private final WorkoutSetEntityMapper workoutSetEntityMapper;
  private final EntityManager entityManager;

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();

    if (session.getId() != null) {
      sessionEntity.setId(session.getId());
    }

    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setSplitName(session.getSplitName());
    sessionEntity.setProgramme(session.getProgramme());
    sessionEntity.setLocation(session.getLocation());
    sessionEntity.setUserEntity(entityManager.getReference(UserEntity.class, session.getUserId().toString()));
    sessionEntity.setWorkoutSetEntities(session.getWorkoutSet().stream().map(workoutSetEntityMapper::toEntity).collect(Collectors.toSet()));
    return sessionEntity;
  }

  public List<SessionEntity> toEntity(List<Session> sessions) {
    return sessions.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

  public Session toDomainObject(SessionEntity sessionEntity) {
    Session session = new Session(sessionEntity.getId());
    session.setCreationDateTime(sessionEntity.getCreationDateTime());
    session.setProgramme(sessionEntity.getProgramme());
    session.setSplitName(sessionEntity.getSplitName());
    session.setLocation(sessionEntity.getLocation());
    session.setUserId(sessionEntity.getUserEntity().getId());
    session.getWorkoutSet().addAll(sessionEntity.getWorkoutSetEntities().stream().map(workoutSetEntityMapper::toDomain).collect(Collectors.toList()));

    return session;
  }
}
