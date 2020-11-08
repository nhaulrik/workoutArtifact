package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class WorkoutSetGraphQLServiceTest {

  private final WorkoutSetFacade workoutSetFacade = mock(WorkoutSetFacade.class);
  private final WorkoutSetGraphQLService workoutSetGraphQLService = new WorkoutSetGraphQLService(workoutSetFacade);

  @Test
  public void getWorkoutSet() {

    WorkoutSetDto workoutSetDtoMock = mock(WorkoutSetDto.class);

    Long id = 1L;

    doReturn(Arrays.asList(workoutSetDtoMock))
        .when(workoutSetFacade).getWorkoutSets(any(AbstractSpecification.class));

    List<WorkoutSetDto> workoutSetDtos = workoutSetGraphQLService.getWorkoutSet(Arrays.asList(id));

    assertThat(workoutSetDtos.size(), is(1));
    assertThat(workoutSetDtos.get(0), is(workoutSetDtoMock));
  }

  @Test
  public void getWorkoutSetWithSessionContext() {

    List<Long> workoutSetIds = Arrays.asList(1L, 3L, 5L);

    SessionDto sessionDto = mock(SessionDto.class);

    doReturn(workoutSetIds)
        .when(sessionDto).getWorkoutSetIds();

    WorkoutSetDto workoutSetDto = mock(WorkoutSetDto.class);

    doReturn(Arrays.asList(workoutSetDto))
        .when(workoutSetFacade).getWorkoutSets(new IdsSpecification(workoutSetIds));

    List<WorkoutSetDto> workoutSetDtos = workoutSetGraphQLService.getWorkoutSet(null, sessionDto);
    assertThat(workoutSetDtos, is(Arrays.asList(workoutSetDto)));
  }

  @Test
  public void addWorkoutSet() {

    Integer setNumber = 12;
    Double weight = 80d;
    Integer repetitions = 8;
    Integer repetitionMaximum = 12;
    Boolean single = true;
    Long exerciseId = 2L;
    UUID sessionId = UUID.randomUUID();
    Long id = 1L;

    Boolean resultBoolean = workoutSetGraphQLService.addWorkoutSet(
        id,
        setNumber,
        weight,
        repetitions,
        repetitionMaximum,
        single,
        exerciseId,
        sessionId
    );

    ArgumentCaptor<WorkoutSetDto> arg = ArgumentCaptor.forClass(WorkoutSetDto.class);
    verify(workoutSetFacade).addWorkoutSet(arg.capture());

    WorkoutSetDto workoutSetDto = arg.getValue();

    assertThat(resultBoolean, is(true));
    assertThat(workoutSetDto.getSetNumber(), is(setNumber));
    assertThat(workoutSetDto.getWeight(), is(weight));
    assertThat(workoutSetDto.getRepetitions(), is(repetitions));
    assertThat(workoutSetDto.getRepetitionMaximum(), is(repetitionMaximum));
    assertThat(workoutSetDto.isSingle(), is(single));
    assertThat(workoutSetDto.getExerciseId(), is(exerciseId));
    assertThat(workoutSetDto.getSessionId(), is(sessionId));
  }
}