package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.junit.Test;

public class SessionMapperTest {

  EntityManager entityManager = mock(EntityManager.class);
  private final SessionMapper sessionMapper = new SessionMapper(entityManager);

  @Test
  public void domainToEntity() {
    String location = "home";
    String splitName = "split1";
    Session session = getSession(location);

    WorkoutSetEntity workoutSetEntityMock = mock(WorkoutSetEntity.class);
    Long workoutSetId = session.getWorkoutSetIds().get(0);

    doReturn(workoutSetEntityMock).when(entityManager).getReference(WorkoutSetEntity.class, workoutSetId);
    doReturn(workoutSetId).when(workoutSetEntityMock).getId();

    SessionEntity sessionEntity = sessionMapper.toEntity(session);

    assertThat(sessionEntity.getLocation(), is(equalTo(location)));
    assertThat(sessionEntity.getSplitName(), is(equalTo(splitName)));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
    assertThat(sessionEntity.getWorkoutSetEntities().stream().findFirst().get().getId(), is(session.getWorkoutSetIds().get(0)));
  }

  @Test
  public void domainToEntityFromList() {
    Session session1 = getSession("location1");
    Session session2 = getSession("location2");

    WorkoutSetEntity workoutSetEntityMock = mock(WorkoutSetEntity.class);
    Long workoutSetId = session1.getWorkoutSetIds().get(0);

    doReturn(workoutSetEntityMock).when(entityManager).getReference(WorkoutSetEntity.class, workoutSetId);
    doReturn(workoutSetId).when(workoutSetEntityMock).getId();

    List<SessionEntity> sessionEntities = sessionMapper.toEntity(Arrays.asList(session1, session2));

    assertHasSameProperties(sessionEntities.get(0), session1);
    assertHasSameProperties(sessionEntities.get(1), session2);

    assertThat(sessionEntities.get(1).getLocation(), is(session2.getLocation()));
    assertThat(sessionEntities.get(1).getCreationDateTime(), is(session2.getCreationDateTime()));
  }

  @Test
  public void entityToDomain() {

    UUID someId = UUID.randomUUID();
    LocalDateTime someLocalDateTime = LocalDateTime.now();
    String someLocation = "some_location";

    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setId(someId);
    sessionEntity.setProgramme("program1");
    sessionEntity.setSplitName("split1");
    sessionEntity.setCreationDateTime(someLocalDateTime);
    sessionEntity.setLocation(someLocation);

    UserEntity userEntity = mock(UserEntity.class);
    doReturn(1L)
        .when(userEntity).getId();

    sessionEntity.setUserEntity(userEntity);

    Session session = sessionMapper.toDomainObject(sessionEntity);

    assertThat(session.getId(), is(someId));
    assertThat(session.getCreationDateTime(), is(someLocalDateTime));
    assertThat(session.getLocation(), is(someLocation));
  }

  @Test
  public void domainToDto() {

    UUID someId = UUID.randomUUID();
    String someLocation = "some_location";

    Session session = Session.builder()
        .id(someId)
        .location(someLocation)
        .creationDateTime(LocalDateTime.now())
        .programme("program1")
        .splitName("split1")
        .build();

    SessionDto sessionDto = sessionMapper.toDto(session);

    assertThat(sessionDto.getId(), is(session.getId()));
    assertThat(sessionDto.getLocation(), is(session.getLocation()));
  }

  @Test
  public void dtoToDomain() {

    SessionDto sessionDto = SessionDto.builder()
        .workoutSetIds(Arrays.asList(1L))
        .programme("program1")
        .splitName("split1")
        .localDateTime(LocalDateTime.now())
        .location("HOME")
        .build();

    Session session = sessionMapper.toDomainObject(sessionDto);

    assertThat(session.getLocation(), is(sessionDto.getLocation()));
    assertThat(session.getCreationDateTime(), is(sessionDto.getLocalDateTime()));
    assertThat(session.getWorkoutSetIds(), is(sessionDto.getWorkoutSetIds()));
  }

  private Session getSession(String location) {
    return Session.builder()
        .splitName("split1")
        .programme("program1")
        .id(UUID.randomUUID())
        .creationDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
        .location(location)
        .workoutSetIds(Arrays.asList(1L))
        .build();
  }

  private void assertHasSameProperties(SessionEntity sessionEntity, Session session) {
    assertThat(sessionEntity.getLocation(), is(session.getLocation()));
    assertThat(sessionEntity.getProgramme(), is(session.getProgramme()));
    assertThat(sessionEntity.getCreationDateTime(), is(session.getCreationDateTime()));
    assertThat(sessionEntity.getWorkoutSetEntities().stream().map(WorkoutSetEntity::getId).collect(Collectors.toList()), is(session.getWorkoutSetIds()));
  }

}