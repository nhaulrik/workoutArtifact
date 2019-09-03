package com.workout.workoutArtifact.domain.workoutset.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseIdSpecification;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetTest {

  @Test
  public void exerciseNameSpecificationIsSatisfied() {

    Long id = 1L;
    WorkoutSet workoutSet = getWorkoutSetMock(id);

    ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(
        Arrays.asList(id));

    assertThat(exerciseIdSpecification.isSatisfiedBy(workoutSet), is(true));
  }

  @Test
  public void exerciseNameSpecificationIsNotSatisfied() {

    WorkoutSet.ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(
        Arrays.asList(0L));

    WorkoutSet workoutSet = getWorkoutSetMock(1L);

    assertThat(exerciseIdSpecification.isSatisfiedBy(workoutSet), is(false));
  }

  private WorkoutSet getWorkoutSetMock(Long id) {
    return WorkoutSet.builder()
        .id(id)
        .exerciseId(1L)
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();
  }
}