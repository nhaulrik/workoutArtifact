package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.backend.common.mapper.SessionMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.SessionRepository;
import com.workout.workoutArtifact.domain.model.Session;
import com.workout.workoutArtifact.domain.service.SessionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class SessionServiceTest {

  private SessionRepository sessionRepository = mock(SessionRepository.class);
  private SessionMapper sessionMapper = mock(SessionMapper.class);

  private SessionService sessionService = new SessionService(sessionRepository, sessionMapper);

  @Test
  public void addSession() {

    Session session = Session.builder()
        .location("home")
        .creationDateTime(LocalDateTime.now())
        .workoutSets(new ArrayList<>())
        .build();

    doReturn(mock(SessionEntity.class))
        .when(sessionMapper).toEntity(session);

    String returnString = sessionService.addSession(session);

    assertThat(returnString, is(equalTo("1 session added")));
  }

  @Test
  public void addSessionsByList() {

    Session session = Session.builder()
        .location("home")
        .creationDateTime(LocalDateTime.now())
        .workoutSets(new ArrayList<>())
        .build();

    doReturn(mock(SessionEntity.class))
        .when(sessionMapper).toEntity(session);

    String returnString = sessionService.addSession(Arrays.asList(session));

    assertThat(returnString, is(equalTo("1 sessions added by list")));
  }

}
