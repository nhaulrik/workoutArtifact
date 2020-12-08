package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class SessionEntityRepository implements SessionRepository {

  private final SessionEntityMapper sessionEntityMapper;
  private final SessionJpaRepository sessionJpaRepository;
  private final SessionSpecificationMapper sessionSpecificationMapper;

  @Override
  public List<Session> getSessions(Specification<Session> sessionSpecification) {
    org.springframework.data.jpa.domain.Specification<SessionEntity> jpaSpecification = sessionSpecificationMapper.toJpaSpecification(sessionSpecification);

    List<SessionEntity> sessionEntities = sessionJpaRepository.findAll(jpaSpecification);
    return sessionEntities.stream()
        .map(sessionEntityMapper::toDomainObject)
        .filter(sessionSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public List<UUID> addSessions(List<Session> sessions) {
    List<UUID> sessionIds = new ArrayList<>();

    sessionIds.addAll(sessions.stream()
        .map(sessionEntityMapper::toEntity)
        .map(sessionJpaRepository::save)
        .map(entity -> entity.getId())
        .collect(Collectors.toList()));

    return sessionIds;
  }

  @Override
  @Transactional
  public Boolean deleteSessions(Specification<Session> sessionSpecification) {

    org.springframework.data.jpa.domain.Specification<SessionEntity> jpaSpecification = sessionSpecificationMapper.toJpaSpecification(sessionSpecification);

    List<String> idsToDelete = sessionJpaRepository.findAll(jpaSpecification).stream()
        .map(sessionEntity -> sessionEntity.getId().toString())
        .collect(Collectors.toList());

    sessionJpaRepository.deleteAllByIdIn(idsToDelete);
    return sessionJpaRepository.findAll(jpaSpecification).isEmpty();
  }

  @Override
  public UUID createSession(Session session) {

    SessionEntity sessionEntity = sessionEntityMapper.toEntity(session);
    UUID id = sessionJpaRepository.save(sessionEntity).getId();
    return id;
  }
}
