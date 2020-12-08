package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSetJpaRepository extends JpaRepository<WorkoutSetEntity, String>,
    JpaSpecificationExecutor<WorkoutSetEntity> {
    void deleteAllById(String id);
}
