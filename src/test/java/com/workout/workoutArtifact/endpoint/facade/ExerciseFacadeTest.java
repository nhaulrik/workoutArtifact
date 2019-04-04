package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExerciseFacadeTest {

  @Mock
  ExerciseService exerciseService;

  private ExerciseFacade exerciseFacade;

  private ExerciseMapper exerciseMapper = new ExerciseMapper();

  @Before
  public void before() {
    exerciseFacade = new ExerciseFacade(
        exerciseService,
        exerciseMapper
    );
  }

  @Test
  public void addExercises() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true, BodyPartEnum.CHEST);

    exerciseFacade.addExercises(Arrays.asList(exercise));

    Class<ArrayList<Exercise>> exerciseListClass = (Class<ArrayList<Exercise>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<Exercise>> arg = ArgumentCaptor.forClass(exerciseListClass);
    verify(exerciseService).addExercises(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(exercise)));
  }

  @Test
  public void getExercises() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true, BodyPartEnum.CHEST);

    when(exerciseService.getExercises(anyList()))
        .thenReturn(Arrays.asList(exercise));

    List<ExerciseDto> resultList = exerciseFacade.getExercises(Arrays.asList("1234"));

    assertThat(resultList.get(0), is(exerciseMapper.toDto(exercise)));
  }

}
