package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.MuscleRelation;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.util.Arrays;
import javax.persistence.EntityManager;
import org.junit.Test;

public class ExerciseMapperTest {

  private final EntityManager entityManager = mock(EntityManager.class);
  private final ExerciseMapper exerciseMapper = new ExerciseMapper(entityManager);

  private final String bodyPart = "some_bodypart";
  private final Boolean isCompound = true;
  private final String someName = "some_name";
  private final Long id = 1L;
  private final Long muscleId = 13L;

  @Test
  public void domainToDto() {

    Exercise exercise = Exercise.builder()
        .bodyPart(bodyPart)
        .isCompound(isCompound)
        .name(someName)
        .id(id)
        .muscleRelations(Arrays.asList(new MuscleRelation(muscleId, 100)))
        .build();

    ExerciseDto exerciseDto = exerciseMapper.toDto(exercise);

    assertThat(exerciseDto.getName(), is(exercise.getName()));
    assertThat(exerciseDto.getBodyPart(), is(exercise.getBodyPart()));
    assertThat(exerciseDto.getId(), is(exercise.getId()));
    assertThat(exerciseDto.getIsCompound(), is(exercise.getIsCompound()));
    assertThat(exerciseDto.getMuscleRelations().get(0).getMuscleId(), is(exercise.getMuscleRelations().get(0).getMuscleId()));
    assertThat(exerciseDto.getMuscleRelations().get(0).getUtilization(), is(exercise.getMuscleRelations().get(0).getUtilization()));
  }

  @Test
  public void entityToDomain() {

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setId(id);
    exerciseEntity.setIsCompound(isCompound);
    exerciseEntity.setName(someName);
    exerciseEntity.setBodyPart(bodyPart);

    Exercise exercise = exerciseMapper.toDomainObject(exerciseEntity);

    assertThat(exercise.getId(), is(exerciseEntity.getId()));
    assertThat(exercise.getIsCompound(), is(exerciseEntity.getIsCompound()));
    assertThat(exercise.getName(), is(exerciseEntity.getName()));
    assertThat(exercise.getBodyPart(), is(exerciseEntity.getBodyPart()));
  }

  @Test
  public void dtoToDomain() {

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .id(id)
        .bodyPart(bodyPart)
        .isCompound(isCompound)
        .name(someName)
        .muscleRelations(Arrays.asList(new ExerciseDto.MuscleRelation(muscleId, 100)))
        .build();

    Exercise exercise = exerciseMapper.toDomainObject(exerciseDto);

    assertThat(exercise.getBodyPart(), is(exerciseDto.getBodyPart()));
    assertThat(exercise.getName(), is(exerciseDto.getName()));
    assertThat(exercise.getIsCompound(), is(exerciseDto.getIsCompound()));
    assertThat(exercise.getMuscleRelations().get(0).getMuscleId(), is(exerciseDto.getMuscleRelations().get(0).getMuscleId()));
    assertThat(exercise.getMuscleRelations().get(0).getUtilization(), is(exerciseDto.getMuscleRelations().get(0).getUtilization()));
    assertThat(exercise.getId(), is(exerciseDto.getId()));
  }

  @Test
  public void domainToEntity() {

    doReturn(mock(MuscleEntity.class))
        .when(entityManager).getReference(MuscleEntity.class, muscleId);

    Exercise exercise = Exercise.builder()
        .bodyPart(bodyPart)
        .isCompound(isCompound)
        .name(someName)
        .id(id)
        .muscleRelations(Arrays.asList(new MuscleRelation(muscleId, 100)))
        .build();

    ExerciseEntity exerciseEntity = exerciseMapper.toEntity(exercise);

    assertThat(exerciseEntity.getId(), is(exercise.getId()));
    assertThat(exerciseEntity.getIsCompound(), is(exercise.getIsCompound()));
    assertThat(exerciseEntity.getBodyPart(), is(exercise.getBodyPart()));
    assertThat(exerciseEntity.getName(), is(exercise.getName()));
  }
}