package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SessionEntityRepositoryTest {

  private final SessionMapper sessionMapper = mock(SessionMapper.class);
  private final SessionJpaRepository sessionJpaRepository = mock(SessionJpaRepository.class);
  private final SessionSpecificationMapper sessionSpecificationMapper = mock(
      SessionSpecificationMapper.class);

  private final SessionEntityRepository sessionEntityRepository = new SessionEntityRepository(
      sessionMapper, sessionJpaRepository, sessionSpecificationMapper);

  @Test
  public void getSessions() {

    Specification specification = new MatchAllSpecification();

    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(
        org.springframework.data.jpa.domain.Specification.class);

    SessionEntity sessionEntity = mock(SessionEntity.class);
    Session session = Session.builder()
        .creationDateTime(LocalDateTime.now())
        .location("some_location")
        .id(1337L)
        .build();

    doReturn(jpaSpecification)
        .when(sessionSpecificationMapper).toJpaSpecification(specification);

    doReturn(Arrays.asList(sessionEntity))
        .when(sessionJpaRepository).findAll(jpaSpecification);

    doReturn(session)
        .when(sessionMapper).toDomainObject(sessionEntity);

    List<Session> sessionList = sessionEntityRepository.getSessions(specification);

    assertThat(sessionList.size(), is(1));
    assertThat(sessionList.get(0), is(session));
  }

  @Test
  public void addSessions() {

    Long someId = 1337L;

    Session session = Session.builder()
        .id(someId)
        .location("some_location")
        .creationDateTime(LocalDateTime.now())
        .build();

    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setId(someId);

    doReturn(sessionEntity)
        .when(sessionMapper).toEntity(session);

    doReturn(sessionEntity)
        .when(sessionJpaRepository).save(sessionEntity);

    String resultString = sessionEntityRepository.addSessions(Arrays.asList(session));
    assertThat(resultString.contains(someId.toString()), is(true));
  }
}