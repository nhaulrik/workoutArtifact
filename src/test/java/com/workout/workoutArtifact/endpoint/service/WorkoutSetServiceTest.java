package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.WorkoutSetRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.WorkoutSetService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class WorkoutSetServiceTest {

  WorkoutSetRepository workoutSetRepository = mock(WorkoutSetRepository.class);
  WorkoutSetMapper workoutSetMapper = mock(WorkoutSetMapper.class);

  WorkoutSetService workoutSetService = new WorkoutSetService(workoutSetRepository,
      workoutSetMapper);

  @Test
  public void getWorkoutSets() {

    Exercise exercise = new Exercise(ExerciseEnum.ARNOLD_PRESS, false, BodyPartEnum.SHOULDER, new ArrayList<>());
    WorkoutSet workoutSet = new WorkoutSet(exercise, 99, false);

    ExerciseEntity exerciseEntity = new ExerciseEntity(exercise.getName().toString(), exercise.getIsMultiJoint(), new ArrayList<>(), exercise.getBodyPartString());
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(workoutSet.getRepetitions(), workoutSet.getSingle(), exerciseEntity);

    when(workoutSetRepository.findAll())
       .thenReturn(Arrays.asList(workoutSetEntity));

    doReturn(workoutSet)
        .when(workoutSetMapper).toDomain(workoutSetEntity);

    List<WorkoutSet> workoutSets = workoutSetService.getWorkoutSet();

    assertThat(workoutSets.get(0), is(workoutSet));
  }

}
