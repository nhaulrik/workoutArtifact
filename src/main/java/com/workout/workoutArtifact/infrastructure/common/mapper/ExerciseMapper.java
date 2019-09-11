package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

  public ExerciseDto toDto(Exercise exercise) {
    return new ExerciseDto(
        exercise.getId(),
        exercise.getName(),
        exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE",
        exercise.getBodyPart()
    );
  }

 public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
   return Exercise.builder()
       .id(exerciseEntity.getId())
       .name(exerciseEntity.getName())
       .isMultiJoint(exerciseEntity.getIsMultiJoint())
       .bodyPart(exerciseEntity.getPrimaryBodyPart())
       .build();
 }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    exerciseEntity.setId(exercise.getId());
    return exerciseEntity;
  }

}
