package com.workout.workoutArtifact.domain.workoutset.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.IdsSpecification;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetTest {

  @Test
  public void exerciseIdSpecificationIsSatisfied() {

    Long exerciseId = 1L;

    WorkoutSet workoutSet = WorkoutSet.builder()
        .id(100L)
        .exerciseId(exerciseId)
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();

    ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(
        Arrays.asList(exerciseId));

    assertThat(exerciseIdSpecification.isSatisfiedBy(workoutSet), is(true));
    assertThat(exerciseIdSpecification.getExerciseIds(), is(Arrays.asList(exerciseId)));
  }

  @Test
  public void idsSpecificationSpecificationIsSatisfied() {

    Long id = 1L;

    WorkoutSet.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    WorkoutSet workoutSet = WorkoutSet.builder()
        .id(id)
        .exerciseId(100L)
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();

    assertThat(idsSpecification.isSatisfiedBy(workoutSet), is(true));
    assertThat(idsSpecification.getIds(), is(Arrays.asList(id)));
  }

}