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
  private final UserEntityMapper userEntityMapper;

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
    sessionEntity.setUserEntity(userEntityMapper.toEntity(session.getUser()));
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
        .workoutSet(sessionEntity.getWorkoutSetEntities().stream().map(workoutSetEntityMapper::toDomain).collect(Collectors.toList()))
        .user(userEntityMapper.toDomainObject(sessionEntity.getUserEntity()))
        .build();
  }
}
