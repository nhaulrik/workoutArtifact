package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionMapper {

  private final EntityManager entityManager;

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setSplitName(session.getSplitName());
    sessionEntity.setProgramme(session.getProgramme());
    sessionEntity.setLocation(session.getLocation());
    sessionEntity.setWorkoutSetEntities(session.getWorkoutSetIds().stream().map(id -> entityManager.getReference(WorkoutSetEntity.class, id)).collect(Collectors.toSet()));
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
        .build();
  }

  public Session toDomainObject(SessionDto sessionDto) {
    return Session.builder()
        .programme(sessionDto.getProgramme())
        .splitName(sessionDto.getSplitName())
        .location(sessionDto.getLocation())
        .workoutSetIds(sessionDto.getWorkoutSetIds())
        .creationDateTime(sessionDto.getLocalDateTime() != null ? sessionDto.getLocalDateTime() : LocalDateTime.now())
        .build();
  }

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .programme(session.getProgramme())
        .splitName(session.getSplitName())
        .id(session.getId())
        .location(session.getLocation())
        .workoutSetIds(session.getWorkoutSetIds())
        .build();
  }
}
