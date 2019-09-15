package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class MuscleMapperTest {

  private final MuscleMapper muscleMapper = new MuscleMapper();

  private final String name = "some_name";
  private final String bodyPart = "some_bodypart";
  private final Long id = 1L;
  private final Long exerciseId = 13L;

  @Test
  public void domainToDto() {

    Muscle muscle = Muscle.builder()
        .name(name)
        .bodyPart(bodyPart)
        .exerciseIds(Arrays.asList(exerciseId))
        .id(id)
        .build();

    MuscleDto muscleDto = muscleMapper.toDto(muscle);

    assertThat(muscleDto.getName(), is(muscle.getName()));
    assertThat(muscleDto.getBodyPart(), is(muscle.getBodyPart()));
    assertThat(muscleDto.getExerciseIds(), is(muscle.getExerciseIds()));
    assertThat(muscleDto.getId(), is(muscle.getId()));
  }

  @Test
  public void domainToEntity() {

    Muscle muscle = Muscle.builder()
        .name(name)
        .id(id)
        .bodyPart(bodyPart)
        .exerciseIds(Arrays.asList(exerciseId))
        .build();

    MuscleEntity muscleEntity = muscleMapper.toEntity(muscle);

    assertThat(muscleEntity.getName(), is(muscle.getName()));
    assertThat(muscleEntity.getBodyPart(), is(muscle.getBodyPart()));
    assertThat(muscleEntity.getId(), is(muscle.getId()));
    assertThat(muscleEntity.getExerciseSet().stream().map(ExerciseEntity::getId).collect(Collectors.toList()), is(muscle.getExerciseIds()));
  }

  @Test
  public void entityToDomain() {

    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setId(id);
    muscleEntity.setName(name);
    muscleEntity.setBodyPart(bodyPart);

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setId(exerciseId);
    muscleEntity.setExerciseSet(Stream.of(exerciseEntity).collect(Collectors.toSet()));

    Muscle muscle = muscleMapper.toDomainObject(muscleEntity);

    assertThat(muscle.getId(), is(muscleEntity.getId()));
    assertThat(muscle.getName(), is(muscleEntity.getName()));
    assertThat(muscle.getBodyPart(), is(muscleEntity.getBodyPart()));
    assertThat(muscle.getExerciseIds(), is(muscleEntity.getExerciseSet().stream().map(ExerciseEntity::getId).collect(Collectors.toList())));
  }

  @Test
  public void dtoToDomain() {

    MuscleDto muscleDto = MuscleDto.builder()
        .name(name)
        .id(id)
        .bodyPart(bodyPart)
        .exerciseIds(Arrays.asList(exerciseId))
        .build();

    Muscle muscle = muscleMapper.toDomainObject(muscleDto);

    assertThat(muscle.getId(), is(muscleDto.getId()));
    assertThat(muscle.getName(), is(muscleDto.getName()));
    assertThat(muscle.getBodyPart(), is(muscleDto.getBodyPart()));
    assertThat(muscle.getExerciseIds(), is(muscleDto.getExerciseIds()));
  }
}