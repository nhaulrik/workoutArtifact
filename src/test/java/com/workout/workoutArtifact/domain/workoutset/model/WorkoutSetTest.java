package com.workout.workoutArtifact.domain.workoutset.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseNameSpecification;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetTest {

  @Test
  public void exerciseNameSpecificationIsSatisfied() {

    String exerciseName = "some_name";
    WorkoutSet workoutSet = getWorkoutSetMock(exerciseName);

    WorkoutSet.ExerciseNameSpecification exerciseNameSpecification = new ExerciseNameSpecification(
        Arrays.asList(exerciseName));

    assertThat(exerciseNameSpecification.isSatisfiedBy(workoutSet), is(true));
  }

  @Test
  public void exerciseNameSpecificationIsNotSatisfied() {

    WorkoutSet.ExerciseNameSpecification exerciseNameSpecification = new ExerciseNameSpecification(
        Arrays.asList("some_name"));

    WorkoutSet workoutSet = getWorkoutSetMock("some_other_name");

    assertThat(exerciseNameSpecification.isSatisfiedBy(workoutSet), is(false));
  }

  private WorkoutSet getWorkoutSetMock(String exerciseName) {
    return WorkoutSet.builder()
        .exerciseName(exerciseName)
        .exercise(mock(Exercise.class))
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();
  }
}