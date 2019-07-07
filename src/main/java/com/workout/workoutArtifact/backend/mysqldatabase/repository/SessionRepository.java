package com.workout.workoutArtifact.backend.mysqldatabase.repository;

    import com.workout.workoutArtifact.backend.mysqldatabase.entity.SessionEntity;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
    import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long>,
    JpaSpecificationExecutor<SessionEntity> {

}
