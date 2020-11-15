package com.workout.workoutArtifact.domain.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.ExerciseEntityRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

public class ExerciseEntityRepositoryTest {

  private final ExerciseEntityMapper exerciseEntityMapper = mock(ExerciseEntityMapper.class);
  private final ExerciseJpaRepository exerciseJpaRepository = mock(ExerciseJpaRepository.class);
  private final ExerciseSpecificationMapper exerciseSpecificationMapper = mock(ExerciseSpecificationMapper.class);
  private final ExerciseEntityRepository exerciseEntityRepository = new ExerciseEntityRepository(exerciseEntityMapper, exerciseJpaRepository, exerciseSpecificationMapper);

  @Test
  public void getExercises() {

    String exerciseName = "some_name";

    Specification specification = new NameSpecification(Arrays.asList(exerciseName));
    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(org.springframework.data.jpa.domain.Specification.class);
    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);
    Exercise exercise = Exercise.builder()
        .id(UUID.randomUUID())
        .name(exerciseName)
        .isCompound(true)
        .bodyPart(BodyPartEnum.CHEST.toString())
        .build();
    
    doReturn(jpaSpecification)
        .when(exerciseSpecificationMapper).toJpaSpecification(specification);

    doReturn(Arrays.asList(exerciseEntity))
        .when(exerciseJpaRepository).findAll(any(org.springframework.data.jpa.domain.Specification.class));

    Mockito.doReturn(exercise)
        .when(exerciseEntityMapper).toDomainObject(exerciseEntity);

    List<Exercise> exerciseList = exerciseEntityRepository.getExercises(specification);

    assertThat(exerciseList.size(), is(1));
    assertThat(exerciseList.get(0), CoreMatchers.is(exercise));
  }

  @Test
  public void addExercises() {

    String someExerciseName = "some_exercise";

    Exercise exercise = mock(Exercise.class);

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(someExerciseName);
    exerciseEntity.setIsCompound(false);
    exerciseEntity.setId(UUID.randomUUID().toString());

    Mockito.doReturn(exerciseEntity)
        .when(exerciseEntityMapper).toEntity(exercise);

    Mockito.doReturn(exerciseEntity)
        .when(exerciseJpaRepository).save(exerciseEntity);

    String resultString = exerciseEntityRepository.addExercises(Arrays.asList(exercise));
    assertThat(resultString.contains(someExerciseName), is(true));
  }

}