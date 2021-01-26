package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.programme.model.Programme;
import com.workout.workoutArtifact.domain.programme.model.ProgrammeRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgrammeEntityRepository implements ProgrammeRepository {

  @Override
  public List<Programme> getProgrammes(Specification<Programme> programmeSpecification) {
    return null;
  }
}
