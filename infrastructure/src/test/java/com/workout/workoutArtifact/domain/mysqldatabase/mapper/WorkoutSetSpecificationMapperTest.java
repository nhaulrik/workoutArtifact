package com.workout.workoutArtifact.domain.mysqldatabase.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.specification.AndSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.IdsSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetSpecificationMapper;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.mapping.MappingException;

public class WorkoutSetSpecificationMapperTest {

  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper = new WorkoutSetSpecificationMapper();

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void mapsExerciseIdsSpecification() {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new ExerciseIdSpecification(Arrays.asList(1L)));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }

  @Test
  public void mapsIdsSpecification() {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new IdsSpecification(Arrays.asList(1L)));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }

  @Test
  public void mapsMatchAllSpecification() {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new MatchAllSpecification());
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }

  @Test
  public void mapsAndSpecification() {
    AndSpecification andSpecification = new AndSpecification(new IdsSpecification(Arrays.asList(1L)), new ExerciseIdSpecification(Arrays.asList(2L)));
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(andSpecification);
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }

  @Test
  public void throwsMappingException() {
    thrown.expect(MappingException.class);
    workoutSetSpecificationMapper.toJpaSpecification(null);
  }
}