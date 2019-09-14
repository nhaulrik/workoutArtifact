package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SessionGraphQLServiceTest {

  private final SessionFacade sessionFacade = mock(SessionFacade.class);
  private final SessionGraphQLService sessionGraphQLService = new SessionGraphQLService(sessionFacade);

  @Test
  public void getSessions() {

    SessionDto sessionDtoMock = mock(SessionDto.class);

    Long id  = 1L;

    doReturn(Arrays.asList(sessionDtoMock))
        .when(sessionFacade).getSessions(any(AbstractSpecification.class));

    List<SessionDto> sessionDtoList = sessionGraphQLService.getSessions(Arrays.asList(id), null);
    assertThat(sessionDtoList, is(Arrays.asList(sessionDtoMock)));
  }
}