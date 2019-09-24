package com.workout.workoutArtifact.domain.workoutset.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSetRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetServiceTest {

  WorkoutSetRepository workoutSetRepository = mock(WorkoutSetRepository.class);

  WorkoutSetService workoutSetService = new WorkoutSetService(workoutSetRepository);

  @Test
  public void getWorkoutSets() {

    WorkoutSet workoutSet = mock(WorkoutSet.class);
    Specification specification = mock(Specification.class);

    doReturn(Arrays.asList(workoutSet))
        .when(workoutSetRepository).getWorkoutSet(specification);

    assertThat(workoutSetService.getWorkoutSet(specification).get(0), is(workoutSet));
  }

  @Test
  public void addWorkoutSet() {

    WorkoutSet workoutSet = mock(WorkoutSet.class);

    workoutSetService.addWorkoutSet(workoutSet);

    verify(workoutSetRepository, times(1)).addWorkoutSet(Arrays.asList(workoutSet));
  }
}
