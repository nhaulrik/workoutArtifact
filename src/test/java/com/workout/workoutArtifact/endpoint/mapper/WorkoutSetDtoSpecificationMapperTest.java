package com.workout.workoutArtifact.endpoint.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto.IdsSpecification;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetDtoSpecificationMapperTest {

  private final WorkoutSetDtoSpecificationMapper workoutSetDtoSpecificationMapper = new WorkoutSetDtoSpecificationMapper();

  @Test
  public void mapsIdsSpecification() {

    Long id = 1L;

    WorkoutSetDto.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
    WorkoutSet.IdsSpecification resultSpecification = (WorkoutSet.IdsSpecification) workoutSetDtoSpecificationMapper.toWorkoutSetSpecification(idsSpecification);

    assertThat(resultSpecification.getIds(), is(Arrays.asList(id)));
  }
}