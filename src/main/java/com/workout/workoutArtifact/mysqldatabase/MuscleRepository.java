package com.workout.workoutArtifact.mysqldatabase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleRepository extends JpaRepository<Muscle, Long> {

  List<Muscle> findByName(String name);
}
