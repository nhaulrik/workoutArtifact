package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseEntityMapper {

  private final WorkoutSetEntityMapper workoutSetEntityMapper;

  private final EntityManager entityManager;

  public WorkoutExercise toDomain(WorkoutExerciseEntity workoutExerciseEntity) {
    return WorkoutExercise.initializeWorkoutExercise(
        workoutExerciseEntity.getId(),
        workoutExerciseEntity.getExerciseNumber(),
        workoutExerciseEntity.getWorkoutSets().stream().map(workoutSetEntityMapper::toDomain).collect(Collectors.toList()),
        workoutExerciseEntity.getExerciseEntity().getId(),
        workoutExerciseEntity.getIsWarmup(),
        workoutExerciseEntity.getSessionEntity().getId()
    );
  }

  public WorkoutExerciseEntity toEntity(WorkoutExercise workoutExercise) {
    WorkoutExerciseEntity workoutExerciseEntity = new WorkoutExerciseEntity();
    workoutExerciseEntity.setId(workoutExercise.getId().toString());
    workoutExerciseEntity.setExerciseNumber(workoutExercise.getExerciseNumber());
    workoutExerciseEntity.setWorkoutSets(workoutExercise.getWorkoutSets().stream().map(workoutSetEntityMapper::toEntity).collect(Collectors.toList()));
    workoutExerciseEntity.setExerciseEntity(entityManager.getReference(ExerciseEntity.class, workoutExercise.getExerciseId().toString()));
    workoutExerciseEntity.setIsWarmup(workoutExercise.getIsWarmup());

    workoutExerciseEntity.getWorkoutSets().forEach(ws -> ws.setWorkoutExerciseEntity(workoutExerciseEntity));
    workoutExerciseEntity.setSessionEntity(entityManager.getReference(SessionEntity.class, workoutExercise.getSessionId().toString()));
    return workoutExerciseEntity;
  }


}
