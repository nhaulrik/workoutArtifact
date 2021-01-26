package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeJpaRepository extends JpaRepository<ProgrammeEntity, String>,
    JpaSpecificationExecutor<ProgrammeEntity> {
}
