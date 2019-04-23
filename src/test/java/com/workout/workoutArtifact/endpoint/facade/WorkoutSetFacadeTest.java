package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
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
public class WorkoutSetFacadeTest {

  WorkoutSetFacade workoutSetFacade;

  @Mock
  private WorkoutSetService workoutSetService;

  private WorkoutSetMapper workoutSetMapper = new WorkoutSetMapper();

  @Before
  public void before() {
    workoutSetFacade = new WorkoutSetFacade(workoutSetService, workoutSetMapper);
  }

  @Test
  public void addWorkoutSets() {

    Exercise exercise = mock(Exercise.class);

    WorkoutSet workoutSet = new WorkoutSet(exercise, 12, true);
    workoutSetFacade.addWorkoutSet(Arrays.asList(workoutSet));

    Class<ArrayList<WorkoutSet>> workoutSetListClass = (Class<ArrayList<WorkoutSet>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<WorkoutSet>> arg = ArgumentCaptor.forClass(workoutSetListClass);
    verify(workoutSetService).addWorkoutSets(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(workoutSet)));
  }

  @Test
  public void getWorkoutSets() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true, BodyPartEnum.CHEST, new ArrayList<>());
    WorkoutSet expectedWorkoutSet = new WorkoutSet(exercise, 12, true);

    doReturn(Arrays.asList(expectedWorkoutSet))
        .when(workoutSetService).getWorkoutSet();

    List<WorkoutSetDto> workoutSetDtos = workoutSetFacade.getWorkoutSets();

    assertThat(workoutSetDtos.get(0), is(workoutSetMapper.toDto(expectedWorkoutSet)));
  }
}
