package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutExerciseEntity;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseEntityMapper {

  private final WorkoutSetEntityMapper workoutSetEntityMapper;

  public WorkoutExercise toDomain(WorkoutExerciseEntity workoutExerciseEntity) {
    return WorkoutExercise.initializeWorkoutExercise(
        workoutExerciseEntity.getId(),
        workoutExerciseEntity.getExerciseNumber(),
        workoutExerciseEntity.getWorkoutSets().stream().map(workoutSetEntityMapper::toDomain).collect(Collectors.toList()));
  }

  public WorkoutExerciseEntity toEntity(WorkoutExercise workoutExercise) {
    WorkoutExerciseEntity workoutExerciseEntity = new WorkoutExerciseEntity();
    workoutExerciseEntity.setId(workoutExercise.getId().toString());
    workoutExerciseEntity.setExerciseNumber(workoutExercise.getExerciseNumber());
    workoutExerciseEntity.setWorkoutSets(workoutExercise.getWorkoutSets().stream().map(workoutSetEntityMapper::toEntity).collect(Collectors.toList()));
    return workoutExerciseEntity;
  }


}
