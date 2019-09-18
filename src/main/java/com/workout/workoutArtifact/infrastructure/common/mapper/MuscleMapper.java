package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import org.springframework.stereotype.Component;

@Component
public class MuscleMapper {

  public MuscleDto toDto(Muscle muscle) {
    return MuscleDto.builder()
        .name(muscle.getName())
        .bodyPart(muscle.getBodyPart())
        .id(muscle.getId())
        .build();
  }

  public Muscle toDomainObject(MuscleDto muscleDto) {
    return Muscle.builder()
        .id(muscleDto.getId())
        .name(muscleDto.getName())
        .bodyPart(muscleDto.getBodyPart())
        .build();
    }

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    return Muscle.builder()
        .id(muscleEntity.getId())
        .name(muscleEntity.getName())
        .bodyPart(muscleEntity.getBodyPart())
        .build();
  }

  public MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getName());
    muscleEntity.setBodyPart(muscle.getBodyPart());
    muscleEntity.setId(muscle.getId());

    return muscleEntity;
  }

}
