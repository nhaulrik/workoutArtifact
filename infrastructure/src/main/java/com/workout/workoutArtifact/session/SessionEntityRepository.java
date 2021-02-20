package com.workout.workoutArtifact.session;

import com.workout.workoutArtifact.mysql.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.SessionJpaRepository;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.SessionRepository;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import com.workout.workoutArtifact.mysql.mapper.SessionEntityMapper;
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
  public UUID save(Session session) {
    SessionEntity sessionEntity = sessionEntityMapper.toEntity(session);
    return sessionJpaRepository.save(sessionEntity).getId();
  }
}
