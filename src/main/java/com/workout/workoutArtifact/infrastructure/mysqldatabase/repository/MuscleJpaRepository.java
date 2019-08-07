package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface MuscleJpaRepository extends JpaRepository<MuscleEntity, Long>,
    JpaSpecificationExecutor<MuscleEntity> {

}
