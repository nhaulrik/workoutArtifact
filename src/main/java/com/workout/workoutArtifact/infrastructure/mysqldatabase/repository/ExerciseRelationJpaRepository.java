package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity
    .CompositeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRelationJpaRepository extends JpaRepository<ExerciseRelationEntity, CompositeIdEntity>,
    JpaSpecificationExecutor<ExerciseRelationEntity> {

}
