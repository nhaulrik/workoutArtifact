package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, String>,
    JpaSpecificationExecutor<ExerciseEntity> {
  void deleteAllById(String id);

}
