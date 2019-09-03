package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.exerciserelation.service.ExerciseRelationService;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.NameSpecification;
import com.workout.workoutArtifact.domain.muscle.service.MuscleService;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.mapper.MuscleDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.common.enums.MuscleEnum;
import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
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
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuscleFacadeTest {

  private MuscleFacade muscleFacade;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private MuscleService muscleService;
  private ExerciseRelationService exerciseRelationService = mock(ExerciseRelationService.class);
  private MuscleMapper muscleMapper = mock(MuscleMapper.class);
  private MuscleDtoSpecificationMapper muscleDtoSpecificationMapper = mock(MuscleDtoSpecificationMapper.class);

  @Before
  public void before() {
    muscleFacade = new MuscleFacade(muscleService, exerciseRelationService, muscleMapper, muscleDtoSpecificationMapper);
  }

  @Test
  public void getMusclesBySpecification() {

    String someName = "name1";

    Muscle muscle = Muscle.builder()
        .name(someName)
        .bodyPart(BodyPartEnum.SHOULDER)
        .build();

    MuscleDto muscleDto = mock(MuscleDto.class);

    doReturn(muscleDto)
        .when(muscleMapper).toDto(muscle);

    MuscleDto.NameSpecification nameDtoSpecification = new MuscleDto.NameSpecification(Arrays.asList(someName));
    Muscle.NameSpecification nameSpecification = new Muscle.NameSpecification(Arrays.asList(someName));

    doReturn(Arrays.asList(muscle))
        .when(muscleService).getMuscles(nameSpecification);

    doReturn(nameSpecification)
        .when(muscleDtoSpecificationMapper).toMuscleSpecification(nameDtoSpecification);

    List<MuscleDto> muscleDtos = muscleFacade.getMuscles(nameDtoSpecification);

    assertThat(muscleDtos.size(), is(1));
    assertThat(muscleDtos.get(0), is(muscleDto));
  }

  @Test
  public void addMuscles() {

    Muscle muscle = Muscle.builder()
        .name(MuscleEnum.BICEPS.toString())
        .bodyPart(BodyPartEnum.SHOULDER)
        .build();

    muscleFacade.addMuscles(Arrays.asList(muscle));

    Class<ArrayList<Muscle>> muscleListClass = (Class<ArrayList<Muscle>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<Muscle>> arg = ArgumentCaptor.forClass(muscleListClass);
    verify(muscleService).addMuscles(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(muscle)));
  }

}
