package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetMapper {

  @Autowired
  ExerciseMapper exerciseMapper;

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getExerciseName().name(),
        workoutSet.getRepetitions(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum());
  }

  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    return new WorkoutSet(
        exerciseMapper.toDomainObject(workoutSetEntity.getExerciseEntity()),
        workoutSetEntity.getRepetitions(),
        workoutSetEntity.isSingle());
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    return new WorkoutSetEntity(
        workoutSet.getRepetitions(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum(),
        exerciseMapper.toEntity(workoutSet.getExercise()));
  }

}
