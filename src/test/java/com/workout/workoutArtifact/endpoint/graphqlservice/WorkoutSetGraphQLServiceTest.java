package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

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
}