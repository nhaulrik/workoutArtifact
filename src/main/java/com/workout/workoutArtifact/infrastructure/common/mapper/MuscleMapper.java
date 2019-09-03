package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MuscleMapper {

  public MuscleDto toDto(Muscle muscle) {
    return new MuscleDto(
        muscle.getName(),
        muscle.getId()
        );
  }

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    return Muscle.builder()
        .id(muscleEntity.getId())
        .name(muscleEntity.getName())
        .bodyPart(BodyPartEnum.valueOf(muscleEntity.getBodyPart()))
        .build();
  }

  public MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getName());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

}
