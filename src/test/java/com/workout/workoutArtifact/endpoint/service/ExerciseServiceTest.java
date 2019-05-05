package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.ExerciseRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;

public class ExerciseServiceTest {


  @Rule
  public ExpectedException thrown = ExpectedException.none();

  ExerciseMapper exerciseMapper = mock(ExerciseMapper.class);

  ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);

  ExerciseService exerciseService = new ExerciseService(exerciseRepository, exerciseMapper);


  @Test
  public void addExercises() {

    Exercise exercise = mock(Exercise.class);

    exerciseService.addExercises(Arrays.asList(exercise));

    verify(exerciseRepository, times(1)).save(exerciseMapper.toEntity(exercise));
  }

  @Test
  public void getExercises() {

    Exercise exercise = mock(Exercise.class);
    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);

    doReturn(exerciseEntity)
        .when(exerciseRepository).findFirstByName(anyString());

    when(exerciseMapper.toDomainObject(exerciseEntity))
        .thenReturn(exercise);

    List<Exercise> exerciseList = exerciseService.getExercises(Arrays.asList("mock_bla"));

    assertThat(exerciseList.get(0), is(exerciseMapper.toDomainObject(exerciseEntity)));
  }


}
