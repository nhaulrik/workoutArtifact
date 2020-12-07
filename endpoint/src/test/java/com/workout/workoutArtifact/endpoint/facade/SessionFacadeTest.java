package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import specification.AbstractSpecification;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.mapper.dto.SessionDtoMapper;
import com.workout.workoutArtifact.endpoint.mapper.specification.SessionDtoSpecificationMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SessionFacadeTest {

  SessionService sessionService = mock(SessionService.class);
  SessionDtoMapper sessionDtoMapper = mock(SessionDtoMapper.class);
  SessionDtoSpecificationMapper sessionDtoSpecificationMapper = mock(SessionDtoSpecificationMapper.class);

  SessionFacade sessionFacade = new SessionFacade(sessionService, sessionDtoMapper, sessionDtoSpecificationMapper);

  @Test
  public void addSessions() {

    String expectedReturnString = "sessions added";

    Session session = mock(Session.class);
    SessionDto sessionDto = mock(SessionDto.class);

    doReturn(session)
        .when(sessionDtoMapper).toDomainObject(sessionDto);

    doReturn(expectedReturnString)
        .when(sessionService).addSessions(Arrays.asList(session));

    String returnString = sessionFacade.addSessions(Arrays.asList(sessionDto)).get(0).toString();

    assertThat(returnString, is(equalTo(expectedReturnString)));
  }

  @Test
  public void getSessions() {

    UUID id = UUID.randomUUID();
    String location = "some_location";

    Session session = new Session(UUID.randomUUID());
    session.setCreationDateTime(LocalDateTime.now());
    session.setLocation("some_location");
    session.setProgramme("some_programme");
    session.setSplitName("some_split_name");

    SessionDto sessionDtoMock = mock(SessionDto.class);

    AbstractSpecification specification = new SessionDto.LocationsSpecification(Arrays.asList(location));

    Session.IdsSpecification idsSpecification = new Session.IdsSpecification(Arrays.asList(id));

    doReturn(idsSpecification)
        .when(sessionDtoSpecificationMapper).toSessionSpecification(specification);

    doReturn(Arrays.asList(session))
        .when(sessionService).getSession(idsSpecification);

    doReturn(sessionDtoMock)
        .when(sessionDtoMapper).toDto(session);

    List<SessionDto> sessionDtoList = sessionFacade.getSessions(specification);

    assertThat(sessionDtoList.size(), is(1));
    assertThat(sessionDtoList.get(0), CoreMatchers.is(sessionDtoMock));
  }
}
