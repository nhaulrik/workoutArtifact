package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseJpaRepository extends JpaRepository<WorkoutExerciseEntity, String>, JpaSpecificationExecutor<WorkoutExerciseEntity> {
  void deleteAllById(String id);
}
