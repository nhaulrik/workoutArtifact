package com.workout.workoutArtifact.endpoint.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.SessionDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.dto.SessionDto.LocationsSpecification;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.NotSpecification;
import com.workout.workoutArtifact.specification.OrSpecification;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.weaver.patterns.OrSignaturePattern;
import org.junit.Test;

public class SessionDtoSpecificationMapperTest {

  private final SessionDtoSpecificationMapper sessionDtoSpecificationMapper = new SessionDtoSpecificationMapper();

  @Test
  public void mapsIdsSpecification() {

    Long id = 1L;

    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    Session.IdsSpecification resultSpecification = (Session.IdsSpecification) sessionDtoSpecificationMapper
        .toSessionSpecification(idsSpecification);

    assertThat(resultSpecification.getIds(), is(Arrays.asList(id)));
  }

  @Test
  public void mapsLocationsSpecification() {

    String location = "some_location";

    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(
        Arrays.asList(location));

    Session.LocationsSpecification resultSpecification = (Session.LocationsSpecification)
        sessionDtoSpecificationMapper
        .toSessionSpecification(locationsSpecification);

    assertThat(resultSpecification.getLocations(), is(Arrays.asList(location)));
  }

  @Test
  public void defaultsToMatchAllSpecification() {
    assertThat(sessionDtoSpecificationMapper.toSessionSpecification(null), is(instanceOf(MatchAllSpecification.class)));
  }

  @Test
  public void mapsAndSpecification() {

    String location = "some_location";
    Long id = 1L;

    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(Arrays.asList(location));
    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    AndSpecification<SessionDto> andSpecification = new AndSpecification<>(locationsSpecification, idsSpecification);

    AndSpecification<Session> sessionAndSpecification = (AndSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(andSpecification);

    assertThat(sessionAndSpecification, is(instanceOf(andSpecification.getClass())));
  }

  @Test
  public void mapsOrSpecification() {

    String location = "some_location";
    Long id = 1L;

    SessionDto.LocationsSpecification locationsSpecification = new LocationsSpecification(Arrays.asList(location));
    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    OrSpecification<SessionDto> orSpecification = new OrSpecification<>(locationsSpecification, idsSpecification);

    OrSpecification<Session> sessionOrSpecification = (OrSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(orSpecification);

    assertThat(sessionOrSpecification, is(instanceOf(orSpecification.getClass())));
  }

  @Test
  public void mapsNotSpecification() {

    Long id = 1L;
    SessionDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    NotSpecification notSpecification = new NotSpecification(idsSpecification);

    NotSpecification<Session> mappedNotSpecification = (NotSpecification<Session>) sessionDtoSpecificationMapper.toSessionSpecification(notSpecification);

    assertThat(mappedNotSpecification, is(instanceOf(NotSpecification.class)));
  }
}