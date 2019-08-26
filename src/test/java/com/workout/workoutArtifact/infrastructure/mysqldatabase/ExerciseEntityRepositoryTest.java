package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ExerciseEntityRepositoryTest {

  private final ExerciseMapper exerciseMapper = mock(ExerciseMapper.class);
  private final ExerciseJpaRepository exerciseJpaRepository = mock(ExerciseJpaRepository.class);
  private final ExerciseSpecificationMapper exerciseSpecificationMapper = mock(ExerciseSpecificationMapper.class);
  private final ExerciseEntityRepository exerciseEntityRepository = new ExerciseEntityRepository(exerciseMapper, exerciseJpaRepository, exerciseSpecificationMapper);

  @Test
  public void getExercises() {

    String exerciseName = "some_name";

    Specification specification = new NameSpecification(Arrays.asList(exerciseName));
    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(org.springframework.data.jpa.domain.Specification.class);
    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);
    Exercise exercise = new Exercise(exerciseName, true, BodyPartEnum.CHEST);

    doReturn(jpaSpecification)
        .when(exerciseSpecificationMapper).toJpaSpecification(specification);

    doReturn(Arrays.asList(exerciseEntity))
        .when(exerciseJpaRepository).findAll(any(org.springframework.data.jpa.domain.Specification.class));

    doReturn(exercise)
        .when(exerciseMapper).toDomainObject(exerciseEntity);

    List<Exercise> exerciseList = exerciseEntityRepository.getExercises(specification);

    assertThat(exerciseList.size(), is(1));
    assertThat(exerciseList.get(0), is(exercise));
  }

  @Test
  public void addExercises() {

    String someExerciseName = "some_exercise";

    Exercise exercise = mock(Exercise.class);

    ExerciseEntity exerciseEntity = new ExerciseEntity(someExerciseName, false, new ArrayList(), "");

    doReturn(exerciseEntity)
        .when(exerciseMapper).toEntity(exercise);

    doReturn(exerciseEntity)
        .when(exerciseJpaRepository).save(exerciseEntity);

    String resultString = exerciseEntityRepository.addExercises(Arrays.asList(exercise));
    assertThat(resultString.contains(someExerciseName), is(true));
  }

}