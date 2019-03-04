package com.workout.workoutArtifact.mysqldatabase;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends CrudRepository<Muscle, Long> {

}
