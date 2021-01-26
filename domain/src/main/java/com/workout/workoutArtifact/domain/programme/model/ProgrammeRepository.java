package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;

public interface ProgrammeRepository {

  List<Programme> getProgrammes(Specification<Programme> programmeSpecification);

}
