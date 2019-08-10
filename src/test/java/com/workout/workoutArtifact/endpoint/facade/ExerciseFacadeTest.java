package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseFacadeTest {

  private ExerciseService exerciseService = mock(ExerciseService.class);
  private ExerciseMapper exerciseMapper = mock(ExerciseMapper.class);
  private ExerciseFacade exerciseFacade = new ExerciseFacade(exerciseService, exerciseMapper);

  @Test
  public void addExercises() {

    Exercise exercise = mock(Exercise.class);

    exerciseFacade.addExercises(Arrays.asList(exercise));

    Class<ArrayList<Exercise>> exerciseListClass = (Class<ArrayList<Exercise>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<Exercise>> arg = ArgumentCaptor.forClass(exerciseListClass);
    verify(exerciseService).addExercises(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(exercise)));
  }

  @Test
  public void getExercises() {

    Exercise exercise = mock(Exercise.class);

    doReturn(Arrays.asList(exercise))
        .when(exerciseService).getExercises(anyList());

    List<ExerciseDto> resultList = exerciseFacade.getExercises(Arrays.asList("1234"));

    assertThat(resultList.get(0), is(exerciseMapper.toDto(exercise)));
  }

}
