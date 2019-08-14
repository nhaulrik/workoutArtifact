package com.workout.workoutArtifact.domain.exercise.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.domain.exercise.model.ExerciseRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.specification.Specification;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExerciseServiceTest {


  @Rule
  public ExpectedException thrown = ExpectedException.none();

  ExerciseMapper exerciseMapper = mock(ExerciseMapper.class);

  ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);

  ExerciseService exerciseService = new ExerciseService(exerciseRepository);


  @Test
  public void addExercises() {

    Exercise exercise = mock(Exercise.class);

    exerciseService.addExercises(Arrays.asList(exercise));

    verify(exerciseRepository, times(1)).addExercises(Arrays.asList(exercise));
  }

  @Test
  public void getExercises() {

    Specification<Exercise> exerciseSpecification = mock(Specification.class);

    Exercise exercise = mock(Exercise.class);

    doReturn(Arrays.asList(exercise))
        .when(exerciseRepository).getExercises(exerciseSpecification);

    List<Exercise> exerciseList = exerciseService.getExercises(exerciseSpecification);

    assertThat(exerciseList.get(0), is(exercise));
  }


}
