package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme.ProgrammeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeJpaRepository extends JpaRepository<ProgrammeEntity, String>,
    JpaSpecificationExecutor<ProgrammeEntity> {

  void deleteAllByIdIn(List<String> ids);
}
