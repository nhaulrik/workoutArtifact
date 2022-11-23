package com.workout.workoutArtifact.mysql.repository;

import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionJpaRepository extends JpaRepository<SessionEntity, String>,
    JpaSpecificationExecutor<SessionEntity> {

  void deleteAllByIdIn(List<String> ids);

  Set<SessionEntity> findAllByUserEntityId(String id);
}
