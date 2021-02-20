package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.muscle.Muscle;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
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
    muscleEntity.setId(muscle.getId().toString());

    return muscleEntity;
  }

}
