package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface ProgrammeRepository {

  List<Programme> getProgrammes(Specification<Programme> programmeSpecification);

  UUID save(Programme programme);

  Boolean delete(Specification<Programme> programmeSpecification);
}
