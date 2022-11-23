//package com.workout.workoutArtifact.muscle.service;
//
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//
//import java.util.Arrays;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MuscleServiceTest {
//
//  MuscleEntityRepository muscleEntityRepository = mock(MuscleEntityRepository.class);
//  MuscleService muscleService = new MuscleService(muscleEntityRepository);
//
//  @Test
//  public void getMuscles() {
//
//    String someMuscleName = "muscle1";
//    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(someMuscleName));
//
//    Muscle muscle = mock(Muscle.class);
//
//    doReturn(Arrays.asList(muscle))
//        .when(muscleEntityRepository).getMuscles(nameSpecification);
//
//    Muscle resultMuscle = muscleService.getMuscles(nameSpecification).get(0);
//    assertThat(resultMuscle, is(muscle));
//  }
//
//  @Test
//  public void addMuscles() {
//
//    String stringToReturn = "muscle added";
//
//    Muscle chest = mock(Muscle.class);
//
//    doReturn(stringToReturn)
//        .when(muscleEntityRepository).addMuscles(Arrays.asList(chest));
//
//    String resultString = muscleService.addMuscles(Arrays.asList(chest));
//
//    assertThat(resultString, is(stringToReturn));
//  }
//
//}