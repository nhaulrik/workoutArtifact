package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.enums.MuscleEnum;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

  public MuscleDto toDto(Muscle muscle) {
    return new MuscleDto(
        muscle.getMuscle().toString()
    );
  }

  public ExerciseDto toDto(Exercise exercise) {
    ExerciseDto exerciseDto = new ExerciseDto(
        exercise.getName().toString(),
        exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE",
        new ArrayList<>(),
        exercise.getBodyPartString()
    );

    List<String> muscleNames = exercise.getMuscles().stream()
        .map(muscle -> muscle.getMuscle().toString())
        .collect(Collectors.toList());

    exerciseDto.setMuscles(muscleNames);

    return exerciseDto;
  }

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = new Muscle(
        MuscleEnum.valueOf(muscleEntity.getName()),
        BodyPartEnum.valueOf(muscleEntity.getBodyPart())
    );

    muscle.setExerciseList(muscleEntity.getExerciseSet().stream()
        .map(entity -> new Exercise(
                ExerciseEnum.valueOf(entity.getName()),
                entity.getIsMultiJoint(),
                BodyPartEnum.valueOf(entity.getPrimaryBodyPart())
            )
        ).collect(Collectors.toList()));
    return muscle;
  }

  public Exercise toDomainObject(ExerciseEntity exerciseEntity) {

    Exercise exercise = new Exercise(
        ExerciseEnum.valueOf(exerciseEntity.getName()),
        exerciseEntity.getIsMultiJoint(),
        BodyPartEnum.valueOf(exerciseEntity.getPrimaryBodyPart())
    );

    exercise.setMuscles(exerciseEntity.getMuscleEntities().stream()
        .map(entity -> new Muscle(
            MuscleEnum.valueOf(entity.getName()),
            BodyPartEnum.valueOf(entity.getBodyPart()))
        )
        .collect(Collectors.toList()));
    return exercise;
  }

  public MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getMuscle().toString());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

  public ExerciseEntity toEntity(Exercise exercise) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(exercise.getName().toString());
    exerciseEntity.setIsMultiJoint(exercise.getIsMultiJoint());
    return exerciseEntity;
  }

}
