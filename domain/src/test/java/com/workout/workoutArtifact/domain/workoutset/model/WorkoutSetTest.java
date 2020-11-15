package com.workout.workoutArtifact.domain.workoutset.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.IdsSpecification;
import java.util.Arrays;
import java.util.UUID;
import org.junit.Test;

public class WorkoutSetTest {

  @Test
  public void exerciseIdSpecificationIsSatisfied() {

    Exercise exercise = mock(Exercise.class);

    WorkoutSet workoutSet = WorkoutSet.builder()
        .id(UUID.randomUUID())
        .exercise(exercise)
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();

    ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(
        Arrays.asList(UUID.randomUUID()));

    assertThat(exerciseIdSpecification.isSatisfiedBy(workoutSet), is(true));
    assertThat(exerciseIdSpecification.getExerciseIds(), is(Arrays.asList(1L)));
  }

  @Test
  public void idsSpecificationSpecificationIsSatisfied() {

    UUID id = UUID.randomUUID();
    Exercise exercise = mock(Exercise.class);

    WorkoutSet.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));

    WorkoutSet workoutSet = WorkoutSet.builder()
        .id(id)
        .exercise(exercise)
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