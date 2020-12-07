//package com.workout.workoutArtifact.endpoint.mapper;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.domain.muscle.model.Muscle;
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
//import com.workout.workoutArtifact.endpoint.dto.MuscleDto.IdsSpecification;
//import com.workout.workoutArtifact.endpoint.dto.MuscleDto.NameSpecification;
//import com.workout.workoutArtifact.endpoint.mapper.specification.MuscleDtoSpecificationMapper;
//import java.util.Arrays;
//import org.junit.Test;
//
//public class MuscleDtoSpecificationMapperTest {
//
//  private final MuscleDtoSpecificationMapper muscleDtoSpecificationMapper = new MuscleDtoSpecificationMapper();
//
//  @Test
//  public void mapsNameSpecification() {
//
//    String name = "some_name";
//
//    MuscleDto.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(name));
//    Muscle.NameSpecification resultSpecification = (Muscle.NameSpecification) muscleDtoSpecificationMapper.toMuscleSpecification(nameSpecification);
//
//    assertThat(resultSpecification.getNames(), is(Arrays.asList(name)));
//  }
//
//  @Test
//  public void mapsIdsSpecification() {
//
//    Long id = 1L;
//
//    MuscleDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//    Muscle.IdsSpecification resultSpecification = (Muscle.IdsSpecification) muscleDtoSpecificationMapper.toMuscleSpecification(idsSpecification);
//
//    assertThat(resultSpecification.getIds(), is(Arrays.asList(id)));
//  }
//
//  @Test
//  public void defaultsToMatchAllSpecification() {
//    AbstractSpecification resultSpecification = muscleDtoSpecificationMapper.toMuscleSpecification(null);
//    assertThat(resultSpecification, is(instanceOf(MatchAllSpecification.class)));
//  }
//}