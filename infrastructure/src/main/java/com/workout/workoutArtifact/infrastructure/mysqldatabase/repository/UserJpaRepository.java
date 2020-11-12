package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long>,
    JpaSpecificationExecutor<UserEntity> {

}
