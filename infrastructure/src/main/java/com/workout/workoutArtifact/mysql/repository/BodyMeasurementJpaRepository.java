package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.BodyMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementJpaRepository extends JpaRepository<BodyMeasurementEntity, String>, JpaSpecificationExecutor<BodyMeasurementEntity> {

}
