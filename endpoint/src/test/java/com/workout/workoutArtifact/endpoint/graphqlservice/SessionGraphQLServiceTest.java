package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

@Ignore
public class SessionGraphQLServiceTest {

  private final SessionFacade sessionFacade = mock(SessionFacade.class);
  private final SessionGraphQLService sessionGraphQLService = new SessionGraphQLService(sessionFacade);

  @Test
  public void getSessions() {

    SessionDto sessionDtoMock = mock(SessionDto.class);

    UUID id = UUID.randomUUID();

    doReturn(Arrays.asList(sessionDtoMock))
        .when(sessionFacade).getSessions(any(AbstractSpecification.class));

    List<SessionDto> sessionDtoList = sessionGraphQLService.getSessions(Arrays.asList(id), null, null, null, null, null, null, null);
    assertThat(sessionDtoList, is(Arrays.asList(sessionDtoMock)));
  }

  @Test
  public void addSession() {

    String location = "location";
    String programme = "program1";
    String splitName = "split1";
    String time = "12-11-2019 15:30";
    UUID userId = UUID.randomUUID();
    Long workoutSetId = 1L;

    sessionGraphQLService.addSession(
        null,
        location,
        programme,
        splitName,
        time,
        Arrays.asList(workoutSetId),
        userId
    );

    ArgumentCaptor<List<SessionDto>> arg = ArgumentCaptor.forClass(ArrayList.class);
    verify(sessionFacade).addSessions(arg.capture());

    SessionDto sessionDto = arg.getValue().get(0);

    assertThat(sessionDto.getLocation(), is(location));
    assertThat(sessionDto.getProgramme(), is(programme));
    assertThat(sessionDto.getSplitName(), is(splitName));
    assertThat(sessionDto.getLocalDateTime(), is(notNullValue()));
    assertThat(sessionDto.getWorkoutSetIds(), is(Arrays.asList(workoutSetId)));
  }
}