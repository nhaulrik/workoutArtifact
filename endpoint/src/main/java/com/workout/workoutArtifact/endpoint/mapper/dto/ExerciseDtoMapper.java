package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseDtoMapper {

  public ExerciseDto toDto(Exercise exercise) {
    return ExerciseDto.builder()
        .id(exercise.getId())
        .name(exercise.getName())
        .isCompound(exercise.getIsCompound())
        .bodyPart(exercise.getBodyPart())
        .build();
  }

  public Exercise toDomainObject(ExerciseDto exerciseDto) {
    return Exercise.builder()
        .id(exerciseDto.getId())
        .name(exerciseDto.getName())
        .isCompound(exerciseDto.getIsCompound())
        .bodyPart(exerciseDto.getBodyPart())
        .build();
  }


}
