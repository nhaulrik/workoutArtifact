package com.workout.workoutArtifact.domain.programme.model;

import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Phase {

  private final UUID id;
  private final UUID programmeId;
  private final Integer number;
  private final String description;
  private final List<Split> splitList;

  public Phase(UUID id, UUID programmeId, Integer number, String description, List<Split> splitList) {
    this.id = id;
    this.programmeId = programmeId;
    this.number = number;
    this.description = description;
    this.splitList = splitList;
  }

  public static Phase instantiate(UUID id, UUID programmeId, Integer number, String description, List<Split> splitList) {
    return new Phase(id, programmeId, number, description, splitList);
  }
}
