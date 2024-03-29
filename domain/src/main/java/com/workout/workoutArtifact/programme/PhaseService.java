package com.workout.workoutArtifact.programme;

import com.workout.workoutArtifact.programme.Programme.IdsSpecification;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhaseService {

  private final ProgrammeRepository programmeRepository;


  public UUID postPhase(UUID id, String name, String description, Integer number, UUID programmeId) {

    Optional<Programme> programmeOptional = programmeRepository.getProgrammes(new IdsSpecification(Arrays.asList(programmeId))).stream().findFirst();

    if (programmeOptional.isPresent()) {
      Programme programme = programmeOptional.get();

      Optional<Phase> phaseOptional = programme.getPhase(id);

      Phase phase;
      if (phaseOptional.isPresent()) {
        phase = phaseOptional.get();

        phase.updateNumber(number);
        phase.updateName(name);
        phase.updateDescription(description);
      } else {
        phase = Phase.createNew(
            programmeId,
            number,
            name,
            description
        );
        programme.addPhase(phase);
      }
      programmeRepository.save(programme);
      return phase.getId();
    } else {
      throw new RuntimeException(String.format("could not find programme with id: %s", programmeId));
    }
  }

  public Boolean deletePhase(UUID id) {
    Optional<Programme> programmeOptional = programmeRepository.getProgrammes(new Programme.PhaseIdsSpecification(Arrays.asList(id))).stream().findFirst();

    if (programmeOptional.isPresent()) {
      Programme programme = programmeOptional.get();
      programme.removePhase(id);

      programmeRepository.save(programme);
    } else {
      throw new RuntimeException(String.format("could not find phase with id: %s", id));
    }
    return true;
  }
}
