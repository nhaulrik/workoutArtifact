package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

  public ExerciseDto toDto(Exercise exercise) {
    return new ExerciseDto(
        exercise.getName(),
        exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE",
        exercise.getBodyPartString()
    );
  }

 public Exercise toDomainObject(ExerciseEntity exerciseEntity) {

    return new Exercise(
        exerciseEntity.getName(),
        exerciseEntity.getIsMultiJoint(),
        BodyPartEnum.valueOf(exerciseEntity.getPrimaryBodyPart())
    );
  }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName().toString());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    return exerciseEntity;
  }

}
