//package com.workout.workoutArtifact.workoutset.model;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.mock;
//
//import com.workout.workoutArtifact.exercise.Exercise;
//import com.workout.workoutArtifact.workoutset.WorkoutSet.ExerciseIdSpecification;
//import com.workout.workoutArtifact.workoutset.WorkoutSet.IdsSpecification;
//import java.util.Arrays;
//import java.util.UUID;
//import org.junit.Test;
//
//public class WorkoutSetTest {
//
//  @Test
//  public void exerciseIdSpecificationIsSatisfied() {
//
//    Exercise exercise = mock(Exercise.class);
//
//    WorkoutSet workoutSet = WorkoutSet.createWorkoutSet(
//        UUID.randomUUID(),
//        exercise,
//        false,
//        0d,
//        1,
//        1,
//        1);
//
//    ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(
//        Arrays.asList(UUID.randomUUID()));
//
//    assertThat(exerciseIdSpecification.isSatisfiedBy(workoutSet), is(true));
//    assertThat(exerciseIdSpecification.getExerciseIds(), is(Arrays.asList(1L)));
//  }
//
//  @Test
//  public void idsSpecificationSpecificationIsSatisfied() {
//
//    UUID id = UUID.randomUUID();
//    Exercise exercise = mock(Exercise.class);
//
//    WorkoutSet.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(id));
//
//    WorkoutSet workoutSet = WorkoutSet.createWorkoutSet(
//        UUID.randomUUID(),
//        exercise,
//        false,
//        0d,
//        1,
//        1,
//        1);
//
//    assertThat(idsSpecification.isSatisfiedBy(workoutSet), is(true));
//    assertThat(idsSpecification.getIds(), is(Arrays.asList(id)));
//  }
//
//}