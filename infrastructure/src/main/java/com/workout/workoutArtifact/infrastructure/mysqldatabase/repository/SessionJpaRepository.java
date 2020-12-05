package com.workout.workoutArtifact.infrastructure.mysqldatabase.repository;

    import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
    import java.util.List;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
    import org.springframework.stereotype.Repository;

@Repository
public interface SessionJpaRepository extends JpaRepository<SessionEntity, String>,
    JpaSpecificationExecutor<SessionEntity> {
        void deleteAllByIdIn(List<String> ids);
}
