package com.workout.workoutArtifact.backend.mysqldatabase.repository;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long>,
    JpaSpecificationExecutor<ExerciseEntity> {

}
