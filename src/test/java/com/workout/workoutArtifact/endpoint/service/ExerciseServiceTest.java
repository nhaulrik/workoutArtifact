package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.ExerciseRepository;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;

public class ExerciseServiceTest {


  @Rule
  public ExpectedException thrown = ExpectedException.none();

  ExerciseMapper mapper = new ExerciseMapper();

  ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);

  ExerciseService exerciseService = new ExerciseService(exerciseRepository, mapper);


  @Test
  public void addExercises() {

    Exercise exercise = new Exercise(ExerciseEnum.BARBELL_CHEST_PRESS, true, BodyPartEnum.CHEST);

    exerciseService.addExercises(Arrays.asList(exercise));

    verify(exerciseRepository, times(1)).save(mapper.toEntity(exercise));
  }

  @Test
  public void getExercises() {

    ExerciseEnum barbellChestPress = ExerciseEnum.BARBELL_CHEST_PRESS;
    BodyPartEnum chest = BodyPartEnum.CHEST;

    // TODO: 04-04-2019 is this right?
    Exercise exercise = new Exercise(barbellChestPress, true, chest);
    ExerciseEntity exerciseEntity = new ExerciseEntity(barbellChestPress.toString(), true,
        new ArrayList<>(), chest.toString());

    when(exerciseRepository
        .findAll(ArgumentMatchers.any(org.springframework.data.jpa.domain.Specification.class)))
        .thenReturn(Arrays.asList(exerciseEntity));

    List<Exercise> exerciseList = exerciseService
        .getExercises(Arrays.asList(ExerciseEnum.BARBELL_CHEST_PRESS.toString()));

    assertThat(exerciseList.get(0), is(mapper.toDomainObject(exerciseEntity)));
  }


}
