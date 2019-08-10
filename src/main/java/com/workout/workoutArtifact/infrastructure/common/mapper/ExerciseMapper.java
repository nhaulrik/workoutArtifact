package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

  @Autowired
  MuscleMapper muscleMapper;

  public ExerciseDto toDto(Exercise exercise) {
    ExerciseDto exerciseDto = new ExerciseDto(
        exercise.getName().toString(),
        exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE",
        new ArrayList<>(),
        exercise.getBodyPartString()
    );

    List<String> muscleNames = exercise.getMuscles().stream()
        .map(muscle -> muscle.getName())
        .collect(Collectors.toList());

    exerciseDto.setMuscles(muscleNames);

    return exerciseDto;
  }

 public Exercise toDomainObject(ExerciseEntity exerciseEntity) {

    Exercise exercise = new Exercise(
        exerciseEntity.getName(),
        exerciseEntity.getIsMultiJoint(),
        BodyPartEnum.valueOf(exerciseEntity.getPrimaryBodyPart()),
        exerciseEntity.getMuscleEntities().stream().map(muscleMapper::toDomainObject).collect(Collectors.toList())
    );

    exercise.setMuscles(exerciseEntity.getMuscleEntities().stream()
        .map(entity -> Muscle.builder()
            .name(entity.getName())
            .bodyPart(BodyPartEnum.valueOf(entity.getBodyPart()))
            .build()
        )
        .collect(Collectors.toList()));
    return exercise;
  }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName().toString());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    exerciseEntity.setMuscleEntities(exercise.getMuscles().stream().map(muscleMapper::toEntity).collect(Collectors.toSet()));
    return exerciseEntity;
  }

}
