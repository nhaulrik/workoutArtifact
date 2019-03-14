package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.MuscleEnum;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MuscleFacadeTest {

  private MuscleFacade muscleFacade;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private MuscleService muscleService;

  @Before
  public void before() {
    muscleFacade = new MuscleFacade(muscleService);
  }

  @Test
  public void addMuscles() {

    Muscle muscle = new Muscle(MuscleEnum.BICEPS, BodyPartEnum.SHOULDER);

    muscleFacade.addMuscles(Arrays.asList(muscle));

    Class<ArrayList<Muscle>> muscleListClass = (Class<ArrayList<Muscle>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<Muscle>> arg = ArgumentCaptor.forClass(muscleListClass);
    verify(muscleService).addMuscles(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(muscle)));
  }

  @Test
  public void getMuscles() {

    Muscle expectedMuscle1 = new Muscle(MuscleEnum.BICEPS, BodyPartEnum.ARM);
    Muscle expectedMuscle2 = new Muscle(MuscleEnum.TRAPS, BodyPartEnum.ARM);

    when(muscleService.getMuscles(anyList()))
        .thenReturn(Arrays.asList(expectedMuscle1, expectedMuscle2));

    List<Muscle> muscles = muscleFacade.getMusclesByName(new ArrayList<>());

    assertThat(muscles.get(0), is(expectedMuscle1));
    assertThat(muscles.get(1), is(expectedMuscle2));
  }

  @Test
  public void invalidMuscleNamesThrowsException() {

    String invalidMuscleName = "!_INVALID Name";
    String validMuscleName = "Valid Name";

    thrown.expect(MuscleException.class);
    thrown.expectMessage(ErrorCodes.ILLEGAL_MUSCLE_NAME.getMessage());

    muscleFacade.getMusclesByName(Arrays.asList(validMuscleName, invalidMuscleName));
  }


}
