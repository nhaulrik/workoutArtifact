package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionMapper {

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setLocation(session.getLocation());
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

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .id(session.getId())
        .location(session.getLocation())
        .build();
  }
}
