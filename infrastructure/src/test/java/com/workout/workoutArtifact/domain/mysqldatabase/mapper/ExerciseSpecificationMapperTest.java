package com.workout.workoutArtifact.domain.mysqldatabase.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.exercise.Exercise.NameSpecification;
import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.mapper.ExerciseSpecificationMapper;
import java.util.Arrays;
import org.junit.Test;

public class ExerciseSpecificationMapperTest {

  private final ExerciseSpecificationMapper exerciseSpecificationMapper = new ExerciseSpecificationMapper();

  @Test
  public void mapsNameSpecification() {
    org.springframework.data.jpa.domain.Specification<ExerciseEntity> jpaSpecification = exerciseSpecificationMapper.toJpaSpecification(new NameSpecification(Arrays.asList("some_name")));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }
}