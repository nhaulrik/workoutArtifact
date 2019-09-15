package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class WorkoutSetMapperTest {

  WorkoutSetMapper workoutSetMapper = new WorkoutSetMapper();

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
        .exerciseId(1L)
        .exerciseId(1L)
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