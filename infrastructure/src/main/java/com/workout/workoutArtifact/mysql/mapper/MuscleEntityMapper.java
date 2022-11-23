package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.muscle.Muscle;
import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import org.springframework.stereotype.Component;

@Component
public class MuscleEntityMapper {

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    return Muscle.instantiate(muscleEntity.getId(), muscleEntity.getName(), muscleEntity.getBodyPart());
  }

  public MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getName());
    muscleEntity.setBodyPart(muscle.getBodyPart());
    muscleEntity.setId(muscle.getId().toString());

    return muscleEntity;
  }

}
