package com.workout.workoutArtifact.endpoint.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.ExerciseEnum;
import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.common.MuscleEnum;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.MuscleRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class MuscleServiceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  MuscleRepository muscleRepository = mock(MuscleRepository.class);

  MuscleService muscleService = new MuscleService(muscleRepository);

  @Test
  public void addMuscles() {

    Muscle muscle = new Muscle(MuscleEnum.LOWER_CHEST, BodyPartEnum.CHEST);

    muscleService.addMuscles(Arrays.asList(muscle));

    verify(muscleRepository, times(1)).save(Mapper.toEntity(muscle));
  }

  @Test
  public void getMuscles() {

    Muscle muscle = new Muscle(MuscleEnum.LOWER_CHEST, BodyPartEnum.CHEST);

    when(muscleRepository
        .findAll(ArgumentMatchers.any(org.springframework.data.jpa.domain.Specification.class)))
        .thenReturn(Arrays.asList(Mapper.toEntity(muscle)));

    List<Muscle> muscleList = muscleService.getMuscles(Arrays.asList("1234"));

    assertThat(muscleList.get(0), is(muscle));
  }
}
