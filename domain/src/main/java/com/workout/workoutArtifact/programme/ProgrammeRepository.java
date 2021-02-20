package com.workout.workoutArtifact.programme;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;

interface ProgrammeRepository {

  List<Programme> getProgrammes(Specification<Programme> programmeSpecification);

  UUID save(Programme programme);

  Boolean delete(Specification<Programme> programmeSpecification);
}
