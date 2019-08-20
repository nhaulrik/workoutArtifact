package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.session.service.SessionService;
import java.util.Arrays;
import org.junit.Test;

public class SessionFacadeTest {

  SessionService sessionService = mock(SessionService.class);
  SessionFacade sessionFacade = new SessionFacade(sessionService);

  @Test
  public void addSessions() {

    String expectedReturnString = "sessions added";

    Session session = mock(Session.class);

    doReturn(expectedReturnString)
        .when(sessionService).addSessions(Arrays.asList(session));

    String returnString = sessionFacade.addSessions(Arrays.asList(session));

    assertThat(returnString, is(equalTo(expectedReturnString)));
  }
}
