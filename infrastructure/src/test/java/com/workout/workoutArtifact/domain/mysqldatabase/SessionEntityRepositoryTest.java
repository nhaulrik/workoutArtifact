package com.workout.workoutArtifact.domain.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import specification.MatchAllSpecification;
import specification.Specification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.SessionEntityRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.SessionSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

public class SessionEntityRepositoryTest {

  private final SessionEntityMapper sessionEntityMapper = mock(SessionEntityMapper.class);
  private final SessionJpaRepository sessionJpaRepository = mock(SessionJpaRepository.class);
  private final SessionSpecificationMapper sessionSpecificationMapper = mock(
      SessionSpecificationMapper.class);

  private final SessionEntityRepository sessionEntityRepository = new SessionEntityRepository(
      sessionEntityMapper, sessionJpaRepository, sessionSpecificationMapper);

  @Test
  public void getSessions() {

    Specification specification = new MatchAllSpecification();

    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(
        org.springframework.data.jpa.domain.Specification.class);

    SessionEntity sessionEntity = mock(SessionEntity.class);
    Session session = new Session(UUID.randomUUID());
    session.setCreationDateTime(LocalDateTime.now());
    session.setLocation("some_location");
    session.setProgramme("some_programme");
    session.setSplitName("some_split_name");

    doReturn(jpaSpecification)
        .when(sessionSpecificationMapper).toJpaSpecification(specification);

    doReturn(Arrays.asList(sessionEntity))
        .when(sessionJpaRepository).findAll(jpaSpecification);

    Mockito.doReturn(session)
        .when(sessionEntityMapper).toDomainObject(sessionEntity);

    List<Session> sessionList = sessionEntityRepository.getSessions(specification);

    assertThat(sessionList.size(), is(1));
    assertThat(sessionList.get(0), CoreMatchers.is(session));
  }

  @Test
  public void addSessions() {

    UUID someId = UUID.randomUUID();

    Session session = new Session(someId);
    session.setCreationDateTime(LocalDateTime.now());
    session.setLocation("some_location");
    session.setProgramme("some_programme");
    session.setSplitName("some_split_name");

    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setId(someId);

    Mockito.doReturn(sessionEntity)
        .when(sessionEntityMapper).toEntity(session);

    Mockito.doReturn(sessionEntity)
        .when(sessionJpaRepository).save(sessionEntity);

    String resultString = sessionEntityRepository.addSessions(Arrays.asList(session)).get(0).toString();
    assertThat(resultString.contains(someId.toString()), is(true));
  }
}