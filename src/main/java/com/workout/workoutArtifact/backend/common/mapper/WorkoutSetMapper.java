package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetMapper {

  @Autowired
  ExerciseMapper exerciseMapper;

  @Autowired
  private final ExerciseService exerciseService;

  public WorkoutSetMapper(
      ExerciseService exerciseService) {
    this.exerciseService = exerciseService;
  }

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    return new WorkoutSetDto(
        workoutSet.getExerciseName().name(),
        workoutSet.getRepetitions(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum());
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
// TODO: 23-04-2019 needs test
    Exercise exercise = exerciseService.getExercises(Arrays.asList(workoutSetDto.getExerciseName()))
        .get(0);
    return new WorkoutSet(
        exercise,
        workoutSetDto.getRepetitions(),
        workoutSetDto.getSingle());
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
        exerciseMapper.toEntity(workoutSet.getExercise())); // TODO: 29-04-2019  This part is making trouble. Need some join table stuff on workutsetEntity (like exerciseEntity)
  }

}
