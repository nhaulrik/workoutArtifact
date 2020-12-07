//package com.workout.workoutArtifact.endpoint.mapper;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.domain.session.model.Session;
//import com.workout.workoutArtifact.domain.specification.AndSpecification;
//import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.domain.specification.NotSpecification;
//import com.workout.workoutArtifact.domain.specification.OrSpecification;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto.IdsSpecification;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto.LocationsSpecification;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto.ProgrammeSpecification;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto.SplitNameSpecification;
//import com.workout.workoutArtifact.endpoint.mapper.specification.SessionDtoSpecificationMapper;
//import java.util.Arrays;
//import java.util.UUID;
//import org.junit.Test;
//
//public class SessionDtoSpecificationMapperTest {
//
//  private final SessionDtoSpecificationMapper sessionDtoSpecificationMapper = new SessionDtoSpecificationMapper();
//
//  @Test
//  public void mapsSplitNameSpecification() {
//
//    String splitName = "split1";
//
//    SessionDto.SplitNameSpecification splitNameSpecification = new SplitNameSpecification(splitName);
//
//    Session.SplitNameSpecification resultSpecification = (Session.SplitNameSpecification) sessionDtoSpecificationMapper.toSessionSpecification(splitNameSpecification);
//
//    assertThat(resultSpecification.getSplitName(), is(equalTo(splitName)));
//  }
//
//  @Test
//  public void mapsProgrammeSpecification() {
//
//    String programme = "program1";
//
//    SessionDto.ProgrammeSpecification programmeSpecification = new ProgrammeSpecification(programme);
//
//    Session.ProgrammeSpecification resultSpecification = (Session.ProgrammeSpecification) sessionDtoSpecificationMapper.toSessionSpecification(programmeSpecification);
//
//    assertThat(resultSpecification.getProgramme(), is(equalTo(programme)));
//  }
//
//  @Test
//  public void mapsIdsSpecification() {
//
//    UUID id = UUID.randomUUID();
//
//    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//
//    Session.IdsSpecification resultSpecification = (Session.IdsSpecification) sessionDtoSpecificationMapper
//        .toSessionSpecification(idsSpecification);
//
//    assertThat(resultSpecification.getIds(), is(Arrays.asList(id)));
//  }
//
//  @Test
//  public void mapsLocationsSpecification() {
//
//    String location = "some_location";
//
//    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(
//        Arrays.asList(location));
//
//    Session.LocationsSpecification resultSpecification = (Session.LocationsSpecification)
//        sessionDtoSpecificationMapper
//        .toSessionSpecification(locationsSpecification);
//
//    assertThat(resultSpecification.getLocations(), is(Arrays.asList(location)));
//  }
//
//  @Test
//  public void defaultsToMatchAllSpecification() {
//    assertThat(sessionDtoSpecificationMapper.toSessionSpecification(null), is(instanceOf(MatchAllSpecification.class)));
//  }
//
//  @Test
//  public void mapsAndSpecification() {
//
//    String location = "some_location";
//    UUID id = UUID.randomUUID();
//
//    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(Arrays.asList(location));
//    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//
//    AndSpecification<SessionDto> andSpecification = new AndSpecification<>(locationsSpecification, idsSpecification);
//
//    AndSpecification<Session> sessionAndSpecification = (AndSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(andSpecification);
//
//    assertThat(sessionAndSpecification, is(instanceOf(andSpecification.getClass())));
//  }
//
//  @Test
//  public void mapsOrSpecification() {
//
//    String location = "some_location";
//    UUID id = UUID.randomUUID();
//
//    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(Arrays.asList(location));
//    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//
//    OrSpecification<SessionDto> orSpecification = new OrSpecification<>(locationsSpecification, idsSpecification);
//
//    OrSpecification<Session> sessionOrSpecification = (OrSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(orSpecification);
//
//    assertThat(sessionOrSpecification, is(instanceOf(orSpecification.getClass())));
//  }
//
//  @Test
//  public void mapsNotSpecification() {
//
//    UUID id = UUID.randomUUID();
//    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//
//    NotSpecification notSpecification = new NotSpecification(idsSpecification);
//
//    NotSpecification<Session> mappedNotSpecification = (NotSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(notSpecification);
//
//    assertThat(mappedNotSpecification, is(instanceOf(NotSpecification.class)));
//  }
//}