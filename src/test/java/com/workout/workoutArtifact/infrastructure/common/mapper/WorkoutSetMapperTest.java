package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class WorkoutSetMapperTest {

  ExerciseMapper exerciseMapper = mock(ExerciseMapper.class);
  ExerciseService exerciseService = mock(ExerciseService.class);

  Exercise exerciseMock = mock(Exercise.class);
  ExerciseEntity exerciseEntityMock = mock(ExerciseEntity.class);

  WorkoutSetMapper workoutSetMapper = new WorkoutSetMapper(exerciseMapper, exerciseService);

  @Before
  public void before() {
    doReturn(exerciseEntityMock)
        .when(exerciseMapper).toEntity(exerciseMock);
  }

  @Test
  public void toEntity() {
    WorkoutSet workoutSet = getWorkoutSetForTest();

    WorkoutSetEntity workoutSetEntity = workoutSetMapper.toEntity(workoutSet);

    assertHasSameProperties(workoutSetEntity, workoutSet);
  }

  @Test
  public void toEntityFromList() {
    WorkoutSet workoutSet1 = getWorkoutSetForTest();
    WorkoutSet workoutSet2 = getWorkoutSetForTest();

    List<WorkoutSetEntity> workoutSetEntities = workoutSetMapper.toEntity(Arrays.asList(workoutSet1, workoutSet2));

    assertHasSameProperties(workoutSetEntities.get(0), workoutSet1);
    assertHasSameProperties(workoutSetEntities.get(1), workoutSet2);
  }

  private WorkoutSet getWorkoutSetForTest() {
    return WorkoutSet.builder()
        .setNumber(1)
        .repetitionMaximum(2)
        .single(true)
        .weight(100d)
        .repetitions(8)
        .exerciseName("some exercise")
        .exercise(exerciseMock)
        .build();
  }

  private void assertHasSameProperties(WorkoutSetEntity workoutSetEntity, WorkoutSet workoutSet) {
    assertThat(workoutSetEntity.getSetNumber(), is(equalTo(workoutSet.getSetNumber())));
    assertThat(workoutSetEntity.getRepetitionMaximum(), is(equalTo(workoutSet.getRepetitionMaximum())));
    assertThat(workoutSetEntity.isSingle(), is(equalTo(workoutSet.getSingle())));
    assertThat(workoutSetEntity.getWeight(), is(equalTo(workoutSet.getWeight())));
    assertThat(workoutSetEntity.getRepetitions(), is(equalTo(workoutSet.getRepetitions())));
  }
}
