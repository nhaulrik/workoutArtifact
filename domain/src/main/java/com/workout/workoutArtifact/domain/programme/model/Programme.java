package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
@Getter
public class Programme {

  private final UUID id;
  private String name;
  private String description;
  private final LocalDate creationDate;
  private final List<Phase> phases;

  private Programme(UUID id, String name, String description, LocalDate creationDate, List<Phase> phases) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.creationDate = creationDate;
    this.phases = phases;
  }

  public static Programme instantiate(UUID id, String name, String description, LocalDate creationDate, List<Phase> phases) {
    return new Programme(
        id,
        name, description,
        creationDate,
        phases
    );
  }

  public static Programme createNew(String name, String description) {
    return new Programme(
        UUID.randomUUID(),
        name,
        description,
        LocalDate.now(),
        new ArrayList<>()
    );
  }

  public void updateDescription(String description) {
    if (description != null && !description.equals(this.description)) {
      this.description = description;
      log.info(String.format("Programme with id: %s was updated with a new description", description));
    }
  }

  public void updateName(String name) {
    if (name != null && !name.equals(this.name)) {
      this.name = name;
      log.info(String.format("Programme with id: %s was updated with a new name", name));
    }
  }

  public Optional<Phase> getPhase(UUID id) {
    if (id == null) {
      return Optional.empty();
    } else {
      return this.phases.stream()
          .filter(phase -> id.equals(phase.getId()))
          .findFirst();
    }
  }

  public void addPhase(Phase phase) {
    Assert.notNull(phase, "phase is required");
    this.phases.add(phase);
  }

  public void removePhase(UUID phaseId) {
    this.phases.removeIf(phase -> phaseId.equals(phase.getId()));

  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<Programme> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(Programme programme) {
      return ids.contains(programme.getId());
    }
  }

}

