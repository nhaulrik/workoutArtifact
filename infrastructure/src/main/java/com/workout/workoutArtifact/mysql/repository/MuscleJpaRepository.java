package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.MuscleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MuscleJpaRepository extends JpaRepository<MuscleEntity, Long>,
    JpaSpecificationExecutor<MuscleEntity> {

  MuscleEntity findByName(String name);
  List<MuscleEntity> findAllByExerciseEntitiesId(String id);

}
