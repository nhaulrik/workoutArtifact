//package com.workout.workoutArtifact.session.service;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//
//import com.workout.workoutArtifact.session.Session;
//import com.workout.workoutArtifact.session.SessionRepository;
//import com.workout.workoutArtifact.specification.Specification;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.Ignore;
//import org.junit.Test;
//
//@Ignore
//public class SessionServiceTest {
//
//  private SessionRepository sessionRepository = mock(SessionRepository.class);
//  private SessionService sessionService = new SessionService(sessionRepository);
//
//  @Test
//  public void getSessions() {
//
//    Session session = mock(Session.class);
//    Specification<Session> sessionSpecification = null;
//
//    doReturn(Arrays.asList(session))
//        .when(sessionRepository).getSessions(sessionSpecification);
//
//    List<Session> sessionList = sessionService.getSession(sessionSpecification);
//
//    assertThat(sessionList.size(), is(1));
//    assertThat(sessionList.get(0), is(session));
//  }
//
//  @Test
//  public void addSessions() {
//
//    Session session = mock(Session.class);
//    String someResultString = "some_string";
//
//    doReturn(someResultString)
//        .when(sessionRepository).addSessions(Arrays.asList(session));
//
//    String resultString = sessionService.addSessions(Arrays.asList(session)).get(0).toString();
//
//    assertThat(resultString, is(equalTo(someResultString)));
//  }
//
//}
