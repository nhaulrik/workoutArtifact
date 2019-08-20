package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.domain.session.model.Session;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionMapper {

  private final WorkoutSetMapper workoutSetMapper; // TODO: 14-08-2019 should propably not rely on any other mappers. Consider going away from eager loading and fetch data yourself via specifications as glue

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setLocation(session.getLocation());
    sessionEntity.setWorkoutSetEntities(workoutSetMapper.toEntity(session.getWorkoutSets()));
    return sessionEntity;
  }

  public List<SessionEntity> toEntity(List<Session> sessions) {
    return sessions.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

  public Session toDomainObject(SessionEntity sessionEntity) {
    return Session.builder()
        .creationDateTime(sessionEntity.getCreationDateTime())
        .location(sessionEntity.getLocation())
        .id(sessionEntity.getId())
        .build();
  }
}
