package com.workout.workoutArtifact.domain.programme.service;

import com.workout.workoutArtifact.domain.programme.model.Programme;
import com.workout.workoutArtifact.domain.programme.model.Programme.IdsSpecification;
import com.workout.workoutArtifact.domain.programme.model.ProgrammeRepository;
import com.workout.workoutArtifact.domain.specification.Specification;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgrammeService {

  private final ProgrammeRepository programmeRepository;


  public UUID postProgramme(UUID id, String name, String description, LocalDate creationDate, List<UUID> phaseIds) {

    Optional<Programme> programmeOptional = Optional.empty();
    if (id != null) {
      programmeOptional = programmeRepository.getProgrammes(new IdsSpecification(Arrays.asList(id))).stream().findFirst();
    }

    Programme programme;
    if (programmeOptional.isPresent()) {
      programme = programmeOptional.get();

      programme.updateName(name);
      programme.updateDescription(description);

      // TODO: 26-01-2021 do something here
    } else {
      programme = Programme.createNew(
          name,
          description
      );
    }
    programmeRepository.save(programme);
    return programme.getId();
  }

  public Boolean deleteProgramme(UUID id) {
    return programmeRepository.delete(new Programme.IdsSpecification(Arrays.asList(id)));
  }

  public List<Programme> getProgrammes(Specification<Programme> specification) {
    return programmeRepository.getProgrammes(specification);
  }

}
