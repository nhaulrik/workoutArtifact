//package com.workout.workoutArtifact.domain.mysqldatabase.mapper;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.specification.AndSpecification;
//import com.workout.workoutArtifact.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.workoutset.WorkoutSet.ExerciseIdSpecification;
//import com.workout.workoutArtifact.workoutset.WorkoutSet.IdsSpecification;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetSpecificationMapper;
//import java.util.Arrays;
//import java.util.UUID;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.springframework.data.mapping.MappingException;
//
//public class WorkoutSetSpecificationMapperTest {
//
//  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper = new WorkoutSetSpecificationMapper();
//
//  @Rule
//  public ExpectedException thrown = ExpectedException.none();
//
//  @Test
//  public void mapsExerciseIdsSpecification() {
//    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new ExerciseIdSpecification(Arrays.asList(UUID.randomUUID())));
//    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
//  }
//
//  @Test
//  public void mapsIdsSpecification() {
//    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new IdsSpecification(Arrays.asList(UUID.randomUUID())));
//    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
//  }
//
//  @Test
//  public void mapsMatchAllSpecification() {
//    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new MatchAllSpecification());
//    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
//  }
//
//  @Test
//  public void mapsAndSpecification() {
//    AndSpecification andSpecification = new AndSpecification(new IdsSpecification(Arrays.asList(UUID.randomUUID())), new ExerciseIdSpecification(Arrays.asList(UUID.randomUUID())));
//    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(andSpecification);
//    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
//  }
//
//  @Test
//  public void throwsMappingException() {
//    thrown.expect(MappingException.class);
//    workoutSetSpecificationMapper.toJpaSpecification(null);
//  }
//}