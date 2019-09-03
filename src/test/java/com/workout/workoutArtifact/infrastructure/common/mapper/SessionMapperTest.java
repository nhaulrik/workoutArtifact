package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SessionMapperTest {

  private final SessionMapper sessionMapper = new SessionMapper();

  @Test
  public void toEntity() {
    String location = "home";
    Session session = getSession(location);

    SessionEntity sessionEntity = sessionMapper.toEntity(session);

    assertThat(sessionEntity.getLocation(), is(equalTo(location)));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
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
  }

  @Test
  public void toDomain() {

    Long someId = 1337L;
    LocalDateTime someLocalDateTime = LocalDateTime.now();
    String someLocation = "some_location";

    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setId(someId);
    sessionEntity.setCreationDateTime(someLocalDateTime);
    sessionEntity.setLocation(someLocation);

    Session session = sessionMapper.toDomainObject(sessionEntity);

    assertThat(session.getId(), is(someId));
    assertThat(session.getCreationDateTime(), is(someLocalDateTime));
    assertThat(session.getLocation(), is(someLocation));
  }

  private Session getSession(String location) {
    return Session.builder()
        .id(1L)
        .creationDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
        .location(location)
        .build();
  }

  private void assertHasSameProperties(SessionEntity sessionEntity, Session session) {
    assertThat(sessionEntity.getLocation(), is(session.getLocation()));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
  }

}