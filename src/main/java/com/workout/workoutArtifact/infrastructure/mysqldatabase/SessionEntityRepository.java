package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SessionEntityRepository implements SessionRepository {

  private final SessionMapper sessionMapper;
  private final SessionJpaRepository sessionJpaRepository;
  private final SessionSpecificationMapper sessionSpecificationMapper;

  @Override
  public List<Session> getSessions(Specification<Session> sessionSpecification) {
    org.springframework.data.jpa.domain.Specification<SessionEntity> jpaSpecification = sessionSpecificationMapper.toJpaSpecification(sessionSpecification);

    List<SessionEntity> sessionEntities = sessionJpaRepository.findAll(jpaSpecification);
    return sessionEntities.stream()
        .map(sessionMapper::toDomainObject)
        .filter(sessionSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public List<UUID> addSessions(List<Session> sessions) {
    List<UUID> sessionIds = new ArrayList<>();

    sessionIds.addAll(sessions.stream()
        .map(sessionMapper::toEntity)
        .map(sessionJpaRepository::save)
        .map(entity -> entity.getId())
        .collect(Collectors.toList()));

    return sessionIds;
  }

  @Override
  public Boolean deleteSessions(Specification<Session> sessionSpecification) {

    org.springframework.data.jpa.domain.Specification<SessionEntity> jpaSpecification = sessionSpecificationMapper.toJpaSpecification(sessionSpecification);

    List<SessionEntity> entitiesToDelete = sessionJpaRepository.findAll(jpaSpecification);
    sessionJpaRepository.deleteAll(entitiesToDelete);
    return true;
  }
}
