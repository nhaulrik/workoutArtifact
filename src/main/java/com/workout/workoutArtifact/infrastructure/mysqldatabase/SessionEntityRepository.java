package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.SessionRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
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

    return sessionJpaRepository.findAll(jpaSpecification).stream()
        .map(sessionMapper::toDomainObject)
        .filter(sessionSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public String addSessions(List<Session> sessions) {
    return sessions.stream()
        .map(sessionMapper::toEntity)
        .map(sessionJpaRepository::save)
        .map(entity -> entity.getId())
        .collect(Collectors.toList())
        .toString();
  }
}
