package graphqlservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import specification.AbstractSpecification;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


public class MuscleGraphQLServiceTest {

  private final MuscleFacade muscleFacade = mock(MuscleFacade.class);
  private final MuscleGraphQLService muscleGraphQLService = new MuscleGraphQLService(muscleFacade);

  @Test
  public void addMuscles() {

    String name = "some_name";
    String bodyPart = "some_bodypart";

    MuscleDto muscleDto = MuscleDto.builder()
        .name(name)
        .bodyPart(bodyPart)
        .build();

    muscleGraphQLService.addMuscle(name, bodyPart);

    ArgumentCaptor<List<MuscleDto>> arg = ArgumentCaptor.forClass(ArrayList.class);
    verify(muscleFacade).addMuscles(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(muscleDto)));
  }

  @Test
  public void getMuscles() {

    MuscleDto muscleDtoMock = mock(MuscleDto.class);

    doReturn(Arrays.asList(muscleDtoMock))
        .when(muscleFacade).getMuscles(any(AbstractSpecification.class));

    List<MuscleDto> muscleDtoList = muscleGraphQLService.getMuscles(null);

    assertThat(muscleDtoList, is(Arrays.asList(muscleDtoMock)));
  }

  @Test
  public void getMusclesWithExerciseDtoContext() {

    MuscleDto muscleDtoMock = mock(MuscleDto.class);
    ExerciseDto exerciseDto = mock(ExerciseDto.class);

    doReturn(Arrays.asList(muscleDtoMock))
        .when(muscleFacade).getMuscles(any(AbstractSpecification.class));

    List<MuscleDto> muscleDtoList = muscleGraphQLService.getMuscles(exerciseDto, null);

    assertThat(muscleDtoList, is(Arrays.asList(muscleDtoMock)));
  }
}