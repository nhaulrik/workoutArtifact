package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.ErrorCodes;
import com.workout.workoutArtifact.MuscleException;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
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
  public void addMuscle() {

    String muscleName = "rear delt";

    muscleFacade.addMuscle(muscleName);

    ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
    verify(muscleService).addMuscle(arg.capture());

    assertThat(arg.getValue(), is(muscleName));
  }

  @Test
  public void addTrimmedMuscle() {

    String muscleName = "rear delt ";
    String muscleNameTrimmed = "rear delt";

    muscleFacade.addMuscle(muscleName);

    ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
    verify(muscleService).addMuscle(arg.capture());

    assertThat(arg.getValue(), is(muscleNameTrimmed));
  }

  @Test
  public void addInvalidMuscleNameThrowsMuscleNameException() {

    String muscleName = " biceps !_1337 ";

    thrown.expect(MuscleException.class);
    thrown.expectMessage(ErrorCodes.ILLEGAL_MUSCLE_NAME.getMessage());

    muscleFacade.addMuscle(muscleName);
  }

  @Test
  public void getMuscles() {

    Muscle expectedMuscle1 = Muscle.builder()
        .name("expectedMuscleName1")
        .build();

    Muscle expectedMuscle2 = Muscle.builder()
        .name("expectedMuscleName2")
        .build();

    when(muscleService.getMuscles(anyList()))
        .thenReturn(Arrays.asList(expectedMuscle1, expectedMuscle2));

    List<MuscleDto> muscleDtos = muscleFacade.getMusclesByName(new ArrayList<>());

    assertThat(muscleDtos.get(0), is(Muscle.toDto(expectedMuscle1)));
    assertThat(muscleDtos.get(1), is(Muscle.toDto(expectedMuscle2)));
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
