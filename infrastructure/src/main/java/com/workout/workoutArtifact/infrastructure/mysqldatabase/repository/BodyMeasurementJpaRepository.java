package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.BodyMeasurementEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementJpaRepository extends JpaRepository<BodyMeasurementEntity, String>, JpaSpecificationExecutor<BodyMeasurementEntity> {

}
