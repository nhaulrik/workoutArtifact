package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

  public ExerciseDto toDto(Exercise exercise) {
    return ExerciseDto.builder()
        .id(exercise.getId())
        .name(exercise.getName())
        .isMultiJoint(exercise.getIsMultiJoint())
        .bodyPart(exercise.getBodyPart())
        .muscleIds(exercise.getMuscleIds())
        .build();
  }

 public Exercise toDomainObject(ExerciseEntity exerciseEntity) {
   return Exercise.builder()
       .id(exerciseEntity.getId())
       .name(exerciseEntity.getName())
       .isMultiJoint(exerciseEntity.getIsMultiJoint())
       .bodyPart(exerciseEntity.getBodyPart())
       .build();
 }

 public Exercise toDomainObject(ExerciseDto exerciseDto) {
    return Exercise.builder()
        .id(exerciseDto.getId())
        .name(exerciseDto.getName())
        .isMultiJoint(exerciseDto.getIsMultiJoint())
        .bodyPart(exerciseDto.getBodyPart())
        .build();
 }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName());
    exerciseEntity.setBodyPart(exercise.getBodyPart());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    exerciseEntity.setId(exercise.getId());
    return exerciseEntity;
  }

}
