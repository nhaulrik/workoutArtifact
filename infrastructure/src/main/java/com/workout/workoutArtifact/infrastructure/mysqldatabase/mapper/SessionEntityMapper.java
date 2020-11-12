package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionEntityMapper {

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
    sessionEntity.setWorkoutSetEntities(session.getWorkoutSetIds().stream().map(id -> entityManager.getReference(WorkoutSetEntity.class, id)).collect(Collectors.toSet()));
    sessionEntity.setUserEntity(entityManager.getReference(UserEntity.class, session.getUserId().toString()));
    return sessionEntity;
  }

  public List<SessionEntity> toEntity(List<Session> sessions) {
    return sessions.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

  public Session toDomainObject(SessionEntity sessionEntity) {
    return Session.builder()
        .programme(sessionEntity.getProgramme())
        .splitName(sessionEntity.getSplitName())
        .creationDateTime(sessionEntity.getCreationDateTime())
        .location(sessionEntity.getLocation())
        .id(sessionEntity.getId())
        .workoutSetIds(sessionEntity.getWorkoutSetEntities().stream().map(WorkoutSetEntity::getId).collect(Collectors.toList()))
        .userId(sessionEntity.getUserEntity().getId())
        .build();
  }
}
