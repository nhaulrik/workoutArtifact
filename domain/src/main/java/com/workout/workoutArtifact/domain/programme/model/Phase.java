package com.workout.workoutArtifact.domain.programme.model;

import java.util.List;
import java.util.UUID;

public class Phase {

  private final UUID id;
  private final Integer number;
  private final String description;
  private final List<Split> splitList;

  public Phase(UUID id, Integer number, String description, List<Split> splitList) {
    this.id = id;
    this.number = number;
    this.description = description;
    this.splitList = splitList;
  }
}
