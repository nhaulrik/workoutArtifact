package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import org.springframework.stereotype.Component;

@Component
public class MuscleEntityMapper {

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
