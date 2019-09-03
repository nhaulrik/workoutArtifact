package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation.IdsSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import java.util.Arrays;
import org.junit.Test;

public class ExerciseRelationSpecificationMapperTest {

  private final ExerciseRelationSpecificationMapper exerciseRelationSpecificationMapper = new ExerciseRelationSpecificationMapper();

  @Test
  public void mapsToIdsJpaSpecification() {

    Long someId = 1234L;
    ExerciseRelation.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(someId));

    org.springframework.data.jpa.domain.Specification<ExerciseRelationEntity> jpaSpecification = exerciseRelationSpecificationMapper.toJpaSpecification(idsSpecification);
    assertThat(jpaSpecification, is(instanceOf(org.springframework.data.jpa.domain.Specification.class)));
  }
}