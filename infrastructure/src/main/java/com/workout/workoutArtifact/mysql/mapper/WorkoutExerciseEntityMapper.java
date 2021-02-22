package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.mysql.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseEntityMapper {

  private final WorkoutSetJpaRepository workoutSetJpaRepository;
  private final EntityManager entityManager;

  public WorkoutExercise toDomain(WorkoutExerciseEntity workoutExerciseEntity) {
    return WorkoutExercise.initializeWorkoutExercise(
        workoutExerciseEntity.getId(),
        workoutExerciseEntity.getExerciseNumber(),
        workoutExerciseEntity.getWorkoutSets().stream().map(WorkoutSetEntity::getId).collect(Collectors.toList()),
        workoutExerciseEntity.getExerciseEntity().getId(),
        workoutExerciseEntity.getIsWarmup(),
        workoutExerciseEntity.getSessionEntity().getId()
    );
  }

  public WorkoutExerciseEntity toEntity(WorkoutExercise workoutExercise) {
    WorkoutExerciseEntity workoutExerciseEntity = new WorkoutExerciseEntity();
    workoutExerciseEntity.setId(workoutExercise.getId().toString());
    workoutExerciseEntity.setExerciseNumber(workoutExercise.getExerciseNumber());
    workoutExerciseEntity.setWorkoutSets(workoutSetJpaRepository.findAllById(workoutExercise.getWorkoutSetIds().stream().map(id -> id.toString()).collect(Collectors.toList())));
    workoutExerciseEntity.setExerciseEntity(entityManager.getReference(ExerciseEntity.class, workoutExercise.getExerciseId().toString()));
    workoutExerciseEntity.setIsWarmup(workoutExercise.getIsWarmup());

    workoutExerciseEntity.getWorkoutSets().forEach(ws -> ws.setWorkoutExerciseEntity(workoutExerciseEntity));
    workoutExerciseEntity.setSessionEntity(entityManager.getReference(SessionEntity.class, workoutExercise.getSessionId().toString()));
    return workoutExerciseEntity;
  }


}
