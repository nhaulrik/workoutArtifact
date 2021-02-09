package com.workout.workoutArtifact.domain.programme.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Phase {

  private final UUID id;
  private final UUID programmeId;
  private Integer number;
  private String name;
  private String description;
  private final List<Split> splitList;

  public Phase(UUID id, UUID programmeId, Integer number, String name, String description, List<Split> splitList) {
    this.id = id;
    this.programmeId = programmeId;
    this.number = number;
    this.name = name;
    this.description = description;
    this.splitList = splitList;
  }

  public static Phase instantiate(UUID id, UUID programmeId, Integer number, String name, String description, List<Split> splitList) {
    return new Phase(id, programmeId, number, name, description, splitList);
  }

  public static Phase createNew(
      UUID programmeId, Integer number, String name, String description
  ) {
    return new Phase(
        UUID.randomUUID(),
        programmeId,
        number,
        name,
        description,
        new ArrayList<>()
    );
  }

  public void updateNumber(Integer number) {
    if (number != null && number != this.number) {
      this.number = number;
      log.info(String.format("Phase with id: %s was updated with a new number", number));
    }
  }

  public void updateName(String name) {
    if (name != null && !name.equals(this.name)) {
      this.name = name;
      log.info(String.format("Phase with id: %s was updated with a new name", name));
    }
  }

  public void updateDescription(String description) {
    if (description != null && !description.equals(this.description)) {
      this.description = description;
      log.info(String.format("Phase with id: %s was updated with a new description", description));
    }
  }


  @Value
  public static class IdsSpecification extends AbstractSpecification<Phase> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(Phase phase) {
      return ids.contains(phase.getId());
    }
  }

  @Value
  public static class NamesSpecification extends AbstractSpecification<Phase> {

    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(Phase phase) {
      return names.contains(phase.getName());
    }
  }
}
