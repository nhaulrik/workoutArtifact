package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.model.Session.IdsSpecification;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.mapper.SessionDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.SessionMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SessionFacadeTest {

  SessionService sessionService = mock(SessionService.class);
  SessionMapper sessionMapper = mock(SessionMapper.class);
  SessionDtoSpecificationMapper sessionDtoSpecificationMapper = mock(SessionDtoSpecificationMapper.class);

  SessionFacade sessionFacade = new SessionFacade(sessionService, sessionMapper, sessionDtoSpecificationMapper);

  @Test
  public void addSessions() {

    String expectedReturnString = "sessions added";

    Session session = mock(Session.class);
    SessionDto sessionDto = mock(SessionDto.class);

    doReturn(session)
        .when(sessionMapper).toDomainObject(sessionDto);

    doReturn(expectedReturnString)
        .when(sessionService).addSessions(Arrays.asList(session));

    String returnString = sessionFacade.addSessions(Arrays.asList(sessionDto)).get(0).toString();

    assertThat(returnString, is(equalTo(expectedReturnString)));
  }

  @Test
  public void getSessions() {

    Long id = 1L;
    String location = "some_location";
    Session session = Session.builder()
        .id(id)
        .location("some_location")
        .creationDateTime(LocalDateTime.now())
        .programme("some_programme")
        .splitName("split_name")
        .build();

    SessionDto sessionDtoMock = mock(SessionDto.class);

    AbstractSpecification specification = new SessionDto.LocationsSpecification(Arrays.asList(location));

    Session.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    doReturn(idsSpecification)
        .when(sessionDtoSpecificationMapper).toSessionSpecification(specification);

    doReturn(Arrays.asList(session))
        .when(sessionService).getSession(idsSpecification);

    doReturn(sessionDtoMock)
        .when(sessionMapper).toDto(session);

    List<SessionDto> sessionDtoList = sessionFacade.getSessions(specification);

    assertThat(sessionDtoList.size(), is(1));
    assertThat(sessionDtoList.get(0), is(sessionDtoMock));
  }
}
