package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import java.util.Arrays;
import org.junit.Test;

public class ExerciseMapperTest {

  private final ExerciseMapper exerciseMapper = new ExerciseMapper();

  private final String bodyPart = "some_bodypart";
  private final Boolean isMultiJoint = true;
  private final String someName = "some_name";
  private final Long id = 1L;
  private final Long muscleId = 13L;

  @Test
  public void domainToDto() {

    Exercise exercise = Exercise.builder()
    .bodyPart(bodyPart)
    .isMultiJoint(isMultiJoint)
    .name(someName)
    .id(id)
    .muscleIds(Arrays.asList(muscleId))
    .build();

    ExerciseDto exerciseDto = exerciseMapper.toDto(exercise);

    assertThat(exerciseDto.getName(), is(exercise.getName()));
    assertThat(exerciseDto.getBodyPart(), is(exercise.getBodyPart()));
    assertThat(exerciseDto.getId(), is(exercise.getId()));
    assertThat(exerciseDto.getIsMultiJoint(), is(exercise.getIsMultiJoint()));
    assertThat(exerciseDto.getMuscleIds(), is(exercise.getMuscleIds()));
  }

  @Test
  public void entityToDomain() {

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setId(id);
    exerciseEntity.setIsMultiJoint(isMultiJoint);
    exerciseEntity.setName(someName);
    exerciseEntity.setBodyPart(bodyPart);

    Exercise exercise = exerciseMapper.toDomainObject(exerciseEntity);

    assertThat(exercise.getId(), is(exerciseEntity.getId()));
    assertThat(exercise.getIsMultiJoint(), is(exerciseEntity.getIsMultiJoint()));
    assertThat(exercise.getName(), is(exerciseEntity.getName()));
    assertThat(exercise.getBodyPart(), is(exerciseEntity.getBodyPart()));
  }

  @Test
  public void dtoToDomain() {

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .id(id)
        .bodyPart(bodyPart)
        .isMultiJoint(isMultiJoint)
        .name(someName)
        .muscleIds(Arrays.asList(muscleId))
        .build();

    Exercise exercise = exerciseMapper.toDomainObject(exerciseDto);

    assertThat(exercise.getBodyPart(), is(exerciseDto.getBodyPart()));
    assertThat(exercise.getName(), is(exerciseDto.getName()));
    assertThat(exercise.getIsMultiJoint(), is(exerciseDto.getIsMultiJoint()));
    assertThat(exercise.getMuscleIds(), is(exerciseDto.getMuscleIds()));
    assertThat(exercise.getId(), is(exerciseDto.getId()));
  }

  @Test
  public void domainToEntity() {

    Exercise exercise = Exercise.builder()
        .bodyPart(bodyPart)
        .isMultiJoint(isMultiJoint)
        .name(someName)
        .id(id)
        .muscleIds(Arrays.asList(muscleId))
        .build();

    ExerciseEntity exerciseEntity = exerciseMapper.toEntity(exercise);

    assertThat(exerciseEntity.getId(), is(exercise.getId()));
    assertThat(exerciseEntity.getIsMultiJoint(), is(exercise.getIsMultiJoint()));
    assertThat(exerciseEntity.getBodyPart(), is(exercise.getBodyPart()));
    assertThat(exerciseEntity.getName(), is(exercise.getName()));
  }
}