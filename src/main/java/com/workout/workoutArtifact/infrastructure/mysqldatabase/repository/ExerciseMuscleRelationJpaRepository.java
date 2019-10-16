package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseMuscleRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseMuscleRelationJpaRepository extends JpaRepository<ExerciseMuscleRelationEntity, Long>,
    JpaSpecificationExecutor<ExerciseMuscleRelationEntity> {
}
