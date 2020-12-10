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

  private final WorkoutExerciseEntityMapper workoutExerciseEntityMapper;
  private final EntityManager entityManager;

  public SessionEntity toEntity(Session session) {
    SessionEntity sessionEntity = new SessionEntity();

    sessionEntity.setId(session.getId());
    sessionEntity.setCreationDateTime(session.getCreationDateTime());
    sessionEntity.setSplitName(session.getSplitName());
    sessionEntity.setProgramme(session.getProgramme());
    sessionEntity.setLocation(session.getLocation());
    sessionEntity.setWorkoutExercises(session.getWorkoutExercises().stream().map(workoutExerciseEntityMapper::toEntity).collect(Collectors.toList()));
    sessionEntity.getWorkoutExercises().forEach(workoutExerciseEntity -> workoutExerciseEntity.setSessionEntity(sessionEntity));

    sessionEntity.setUserEntity(entityManager.getReference(UserEntity.class, session.getUserId().toString()));

    return sessionEntity;
  }

  public List<SessionEntity> toEntity(List<Session> sessions) {
    return sessions.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }

  public Session toDomainObject(SessionEntity sessionEntity) {
    Session session = Session.instantiate(
        sessionEntity.getId(),
        sessionEntity.getCreationDateTime(),
        sessionEntity.getProgramme(),
        sessionEntity.getSplitName(),
        sessionEntity.getLocation(),
        sessionEntity.getWorkoutExercises().stream().map(workoutExerciseEntityMapper::toDomain).collect(Collectors.toList()),
        sessionEntity.getUserEntity().getId()
    );

    return session;
  }
}
