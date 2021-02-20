package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.WorkoutSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSetJpaRepository extends JpaRepository<WorkoutSetEntity, String>,
    JpaSpecificationExecutor<WorkoutSetEntity> {
    void deleteAllById(String id);
}
