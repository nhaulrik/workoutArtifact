package com.workout.workoutArtifact.domain.workoutset.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import java.util.Arrays;
import org.junit.Test;

public class WorkoutSetServiceTest {

  WorkoutSetJpaRepository workoutSetRepository = mock(WorkoutSetJpaRepository.class);
  ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
  WorkoutSetMapper workoutSetMapper = mock(WorkoutSetMapper.class);

  WorkoutSetService workoutSetService = new WorkoutSetService(workoutSetRepository,
      workoutSetMapper, exerciseRepository);

  @Test
  public void getWorkoutSets() {

    WorkoutSet workoutSet = mock(WorkoutSet.class);
    WorkoutSetEntity workoutSetEntity = mock(WorkoutSetEntity.class);

    doReturn(Arrays.asList(workoutSetEntity))
        .when(workoutSetRepository).findAll();

    doReturn(workoutSet)
        .when(workoutSetMapper).toDomain(workoutSetEntity);

    assertThat(workoutSetService.getWorkoutSet().get(0), is(workoutSet));
  }

}
