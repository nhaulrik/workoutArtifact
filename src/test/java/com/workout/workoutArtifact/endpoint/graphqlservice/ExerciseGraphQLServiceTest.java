package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ExerciseGraphQLServiceTest {

  private final ExerciseFacade exerciseFacade = mock(ExerciseFacade.class);
  private final ExerciseGraphQLService exerciseGraphQLService = new ExerciseGraphQLService(
      exerciseFacade);

  @Test
  public void getExercisesByNames() {

    String someExerciseName = "some_name";

    ExerciseDto exerciseDto = new ExerciseDto(0L, someExerciseName, "", "");

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService
        .getExercises(Arrays.asList(someExerciseName), null, null);

    assertThat(exerciseDtos.size(), is(1));
    assertThat(exerciseDtos.get(0).getName(), is(someExerciseName));
  }

  @Test
  public void getExercisesByTypes() {

    String someType = "compound";

    ExerciseDto exerciseDto = new ExerciseDto(0L, "", someType, "");

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService
        .getExercises(null, Arrays.asList(someType), null);

    assertThat(exerciseDtos.size(), is(1));
    assertThat(exerciseDtos.get(0).getType(), is(someType));
  }

  @Test
  public void getExercisesByBodyPart() {

    String someBodyPart = "chest";

    ExerciseDto exerciseDto = new ExerciseDto(0L, "", "", someBodyPart);

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService
        .getExercises(null, null, Arrays.asList(someBodyPart));

    assertThat(exerciseDtos.size(), is(1));
    assertThat(exerciseDtos.get(0).getBodyPart(), is(someBodyPart));
  }
}