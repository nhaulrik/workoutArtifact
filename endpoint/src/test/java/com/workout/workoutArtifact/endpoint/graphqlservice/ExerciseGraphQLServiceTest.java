package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.verify;

public class ExerciseGraphQLServiceTest {

  private final ExerciseFacade exerciseFacade = mock(ExerciseFacade.class);
  private final ExerciseGraphQLService exerciseGraphQLService = new ExerciseGraphQLService(
      exerciseFacade);

  @Test
  public void addExercise() {

    String someExerciseName = "some_exercise_name";

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .id(UUID.randomUUID())
        .name(someExerciseName)
        .bodyPart("")
        .isCompound(true)
        .build();

    exerciseGraphQLService.addExercise(
        exerciseDto.getId().toString(),
        exerciseDto.getName(),
        exerciseDto.getBodyPart(),
        exerciseDto.getIsCompound()
    );

    ArgumentCaptor<ExerciseDto> arg = ArgumentCaptor.forClass(ExerciseDto.class);
    verify(exerciseFacade).addExercise(arg.capture());

    assertThat(arg.getValue(), CoreMatchers.is(exerciseDto));
  }

  @Test
  public void getExercisesByNames() {

    String someExerciseName = "some_name";

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .id(UUID.randomUUID())
        .name(someExerciseName)
        .bodyPart("")
        .isCompound(true)
        .build();

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService
        .getExercises(Arrays.asList(someExerciseName), null, null);

    assertThat(exerciseDtos.size(), is(1));
    assertThat(exerciseDtos.get(0).getName(), is(someExerciseName));
  }

  @Test
  public void getExercisesByBodyPart() {

    String someBodyPart = "chest";

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .bodyPart(someBodyPart)
        .name("")
        .id(UUID.randomUUID())
        .isCompound(true)
        .build();

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService
        .getExercises(null, null, Arrays.asList(someBodyPart));

    assertThat(exerciseDtos.size(), is(1));
    assertThat(exerciseDtos.get(0).getBodyPart(), is(someBodyPart));
  }

  @Test
  public void getExercisesWithWorkoutSetDtoContext() {

    WorkoutSetDto workoutSetDto = mock(WorkoutSetDto.class);
    ExerciseDto exerciseDto = mock(ExerciseDto.class);

    doReturn(exerciseDto)
        .when(workoutSetDto).getExerciseId();

    doReturn(Arrays.asList(exerciseDto))
        .when(exerciseFacade).getExercises(any(AbstractSpecification.class));

    List<ExerciseDto> exerciseDtos = exerciseGraphQLService.getExercises(workoutSetDto, null, null, null);

    assertThat(exerciseDtos, is(Arrays.asList(exerciseDto)));
  }
}