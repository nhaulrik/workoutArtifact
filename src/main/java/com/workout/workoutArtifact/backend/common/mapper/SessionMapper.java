package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.domain.model.Session;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionMapper {

  private final WorkoutSetMapper workoutSetMapper;

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

}
