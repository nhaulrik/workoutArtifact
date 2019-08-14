package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WorkoutSetFacadeTest {

  WorkoutSetFacade workoutSetFacade;

  @Mock
  private WorkoutSetService workoutSetService;

  private WorkoutSetMapper workoutSetMapper = mock(WorkoutSetMapper.class);

  @Before
  public void before() {
    workoutSetFacade = new WorkoutSetFacade(workoutSetService, workoutSetMapper);
  }

  @Test
  public void addWorkoutSet() {

    WorkoutSetDto workoutSetDto = mock(WorkoutSetDto.class);
    WorkoutSet mockedWorkoutSet = mock(WorkoutSet.class);
    doReturn(mockedWorkoutSet)
        .when(workoutSetMapper).toDomain(workoutSetDto);

    workoutSetFacade.addWorkoutSet(workoutSetDto);

    ArgumentCaptor<WorkoutSet> arg = ArgumentCaptor.forClass(WorkoutSet.class);
    verify(workoutSetService).addWorkoutSet(arg.capture());

    assertThat(arg.getValue(), is(mockedWorkoutSet));
  }

  @Test
  public void getWorkoutSets() {

    WorkoutSet expectedWorkoutSet = mock(WorkoutSet.class);

    doReturn(Arrays.asList(expectedWorkoutSet))
        .when(workoutSetService).getWorkoutSet();

    List<WorkoutSetDto> workoutSetDtos = workoutSetFacade.getWorkoutSets();

    assertThat(workoutSetDtos.get(0), is(workoutSetMapper.toDto(expectedWorkoutSet)));
  }
}
