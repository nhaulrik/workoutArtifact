package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, String>,
    JpaSpecificationExecutor<ExerciseEntity> {
  void deleteAllById(String id);

}
