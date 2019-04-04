package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.WorkoutSetRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.WorkoutSetService;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class WorkoutSetServiceTest {

  ExerciseMapper mapper = new ExerciseMapper();

  WorkoutSetRepository workoutSetRepository = mock(WorkoutSetRepository.class);
  WorkoutSetService workoutSetService = new WorkoutSetService(workoutSetRepository, mapper);

  @Test
  public void getWorkoutSets() {

    Exercise exercise = new Exercise(ExerciseEnum.ARNOLD_PRESS, false, BodyPartEnum.SHOULDER);
    WorkoutSet workoutSet = new WorkoutSet(exercise, 99, false);

    ExerciseEntity exerciseEntity = mapper.toEntity(exercise);
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(99, false, exerciseEntity);

    when(workoutSetRepository.findAll())
        .thenReturn(Arrays.asList(workoutSetEntity));

    List<WorkoutSet> workoutSets = workoutSetService.getWorkoutSet();

    assertThat(workoutSets.get(0), is(workoutSet));
  }

}
