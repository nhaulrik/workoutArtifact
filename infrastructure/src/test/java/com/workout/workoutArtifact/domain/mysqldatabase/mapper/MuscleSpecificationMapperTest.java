package com.workout.workoutArtifact.domain.mysqldatabase.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.muscle.model.Muscle.BodyPartSpecification;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.NameSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import java.util.Arrays;
import org.junit.Test;

public class MuscleSpecificationMapperTest {

  MuscleSpecificationMapper muscleSpecificationMapper = new MuscleSpecificationMapper();

  @Test
  public void mapsNameSpecification() {
    org.springframework.data.jpa.domain.Specification<MuscleEntity> jpaSpecification = muscleSpecificationMapper.toJpaSpecification(new NameSpecification(Arrays.asList("some_name")));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }

  @Test
  public void mapsBodyPartSpecification() {
    org.springframework.data.jpa.domain.Specification<MuscleEntity> jpaSpecification = muscleSpecificationMapper.toJpaSpecification(new BodyPartSpecification(Arrays.asList("some_body_part")));
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }
}