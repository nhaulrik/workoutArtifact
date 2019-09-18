package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import org.junit.Test;

public class MuscleMapperTest {

  private final MuscleMapper muscleMapper = new MuscleMapper();

  private final String name = "some_name";
  private final String bodyPart = "some_bodypart";
  private final Long id = 1L;

  @Test
  public void domainToDto() {

    Muscle muscle = Muscle.builder()
        .name(name)
        .bodyPart(bodyPart)
        .id(id)
        .build();

    MuscleDto muscleDto = muscleMapper.toDto(muscle);

    assertThat(muscleDto.getName(), is(muscle.getName()));
    assertThat(muscleDto.getBodyPart(), is(muscle.getBodyPart()));
    assertThat(muscleDto.getId(), is(muscle.getId()));
  }

  @Test
  public void domainToEntity() {

    Muscle muscle = Muscle.builder()
        .name(name)
        .id(id)
        .bodyPart(bodyPart)
        .build();

    MuscleEntity muscleEntity = muscleMapper.toEntity(muscle);

    assertThat(muscleEntity.getName(), is(muscle.getName()));
    assertThat(muscleEntity.getBodyPart(), is(muscle.getBodyPart()));
    assertThat(muscleEntity.getId(), is(muscle.getId()));
  }

  @Test
  public void entityToDomain() {

    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setId(id);
    muscleEntity.setName(name);
    muscleEntity.setBodyPart(bodyPart);

    Muscle muscle = muscleMapper.toDomainObject(muscleEntity);

    assertThat(muscle.getId(), is(muscleEntity.getId()));
    assertThat(muscle.getName(), is(muscleEntity.getName()));
    assertThat(muscle.getBodyPart(), is(muscleEntity.getBodyPart()));
  }

  @Test
  public void dtoToDomain() {

    MuscleDto muscleDto = MuscleDto.builder()
        .name(name)
        .id(id)
        .bodyPart(bodyPart)
        .build();

    Muscle muscle = muscleMapper.toDomainObject(muscleDto);

    assertThat(muscle.getId(), is(muscleDto.getId()));
    assertThat(muscle.getName(), is(muscleDto.getName()));
    assertThat(muscle.getBodyPart(), is(muscleDto.getBodyPart()));
  }
}