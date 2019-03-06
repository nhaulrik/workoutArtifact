package com.workout.workoutArtifact.endpoint.service;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.mysqldatabase.MuscleRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

public class MuscleServiceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  MuscleRepository muscleRepository;

  MuscleService muscleService = new MuscleService(muscleRepository);
//
//  @Test
//  public void unknownMuscleThrowsException() {
//
//    thrown.expect(MuscleException.class);
//    thrown.expectMessage(ErrorCodes.UNKNOWN_MUSCLE.getMessage());
//
//    muscleService.addMuscles("unknown name");
//  }

}
