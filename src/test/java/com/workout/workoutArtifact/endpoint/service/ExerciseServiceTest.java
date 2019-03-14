package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.common.ExerciseEnum;
import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.ExerciseRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;

public class ExerciseServiceTest {

  @Autowired
  Mapper mapper;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);

  ExerciseService exerciseService = new ExerciseService(exerciseRepository, mapper);

  @Test
  public void addExercises() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true);

    exerciseService.addExercises(Arrays.asList(exercise));

    verify(exerciseRepository, times(1)).save(mapper.toEntity(exercise));
  }

  @Test
  public void getExercises() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true);
    ExerciseEntity exerciseAsEntity = mapper.toEntity(exercise);

    when(exerciseRepository
        .findAll(ArgumentMatchers.any(org.springframework.data.jpa.domain.Specification.class)))
        .thenReturn(Arrays.asList(exerciseAsEntity));

    List<Exercise> exerciseList = exerciseService.getExercises(Arrays.asList(ExerciseEnum.BARBELL_CHEST_PRESS.toString()));

    assertThat(exerciseList.get(0), is(exercise));
  }


}
