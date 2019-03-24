package com.workout.workoutArtifact.backend.mysqldatabase.repository;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends JpaRepository<MuscleEntity, Long>,
    JpaSpecificationExecutor<MuscleEntity> {

}
