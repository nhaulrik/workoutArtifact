package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.domain.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.REGON.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetMapper {

  private final ExerciseMapper exerciseMapper;
  private final ExerciseService exerciseService;

  public WorkoutSetDto toDto(WorkoutSet workoutSet) {
    WorkoutSetDto workoutSetDto = new WorkoutSetDto(
        workoutSet.getExerciseName(),
        workoutSet.getRepetitions(),
        String.valueOf(workoutSet.getWeight()),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum());

    workoutSetDto.setId(workoutSet.getId());
    return workoutSetDto;
  }

  public WorkoutSet toDomain(WorkoutSetDto workoutSetDto) {
    Exercise exercise = exerciseService.getExercises(Arrays.asList(workoutSetDto.getExerciseName()))
        .get(0);
    WorkoutSet workoutSet = new WorkoutSet(
        exercise.getName(),
        workoutSetDto.getRepetitions(),
        Double.valueOf(workoutSetDto.getWeight()),
        workoutSetDto.getSingle(),
        workoutSetDto.getRepetitionMaximum(),
        exercise);

    workoutSet.setId(workoutSetDto.getId());
    return workoutSet;
  }

  public WorkoutSet toDomain(WorkoutSetEntity workoutSetEntity) {
    WorkoutSet workoutSet = new WorkoutSet(
        workoutSetEntity.getExerciseEntity().getName(),
        workoutSetEntity.getRepetitions(),
        workoutSetEntity.getWeight(),
        workoutSetEntity.isSingle(),
        workoutSetEntity.getRepetitionMaximum(),
        exerciseMapper.toDomainObject(workoutSetEntity.getExerciseEntity())
    );

    workoutSet.setId(workoutSetEntity.getId());
    return workoutSet;
  }

  public WorkoutSetEntity toEntity(WorkoutSet workoutSet) {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(
        workoutSet.getRepetitions(),
        workoutSet.getWeight(),
        workoutSet.getSingle(),
        workoutSet.getRepetitionMaximum()
    );
    workoutSetEntity.setId(workoutSet.getId());
    return workoutSetEntity;
  }

}
