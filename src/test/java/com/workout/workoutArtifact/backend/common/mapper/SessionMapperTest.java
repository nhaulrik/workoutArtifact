package com.workout.workoutArtifact.backend.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.Session;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SessionMapperTest {

  WorkoutSetMapper workoutSetMapper = mock(WorkoutSetMapper.class);

  private final SessionMapper sessionMapper = new SessionMapper(workoutSetMapper);
  WorkoutSet workoutSet = mock(WorkoutSet.class);
  WorkoutSetEntity mockedWorkoutSetEntity = mock(WorkoutSetEntity.class);

  @Before
  public void before() {
    doReturn(Arrays.asList(mockedWorkoutSetEntity))
        .when(workoutSetMapper).toEntity(Arrays.asList(workoutSet));
  }

  @Test
  public void toEntity() {
    String location = "home";
    Session session = getSession(location);

    SessionEntity sessionEntity = sessionMapper.toEntity(session);

    assertThat(sessionEntity.getLocation(), is(equalTo(location)));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
    assertThat(sessionEntity.getWorkoutSetEntities().get(0), is(mockedWorkoutSetEntity));
  }

  @Test
  public void toEntityFromList() {
    Session session1 = getSession("location1");
    Session session2 = getSession("location2");

    List<SessionEntity> sessionEntities = sessionMapper.toEntity(Arrays.asList(session1, session2));

    assertHasSameProperties(sessionEntities.get(0), session1);
    assertHasSameProperties(sessionEntities.get(1), session2);

    assertThat(sessionEntities.get(1).getLocation(), is(session2.getLocation()));
    assertThat(sessionEntities.get(1).getCreationDateTime(), is(session2.getCreationDateTime()));
    assertThat(sessionEntities.get(1).getWorkoutSetEntities().get(0), is(mockedWorkoutSetEntity));
  }

  private Session getSession(String location) {
    return Session.builder()
        .creationDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
        .location(location)
        .workoutSets(Arrays.asList(workoutSet))
        .build();
  }

  private void assertHasSameProperties(SessionEntity sessionEntity, Session session) {
    assertThat(sessionEntity.getLocation(), is(session.getLocation()));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
    assertThat(sessionEntity.getWorkoutSetEntities().get(0), is(mockedWorkoutSetEntity));
  }

}