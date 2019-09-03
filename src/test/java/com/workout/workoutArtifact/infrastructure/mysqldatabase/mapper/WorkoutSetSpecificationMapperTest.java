package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseIdSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetSpecificationMapperTest {

  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper = new WorkoutSetSpecificationMapper();

  @Test
  public void mapsExerciseNamesSpecification() {
    org.springframework.data.jpa.domain.Specification<WorkoutSetEntity> jpaSpecification = workoutSetSpecificationMapper.toJpaSpecification(new ExerciseIdSpecification(Arrays.asList(1L)));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }
}