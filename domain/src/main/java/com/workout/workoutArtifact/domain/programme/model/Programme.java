package com.workout.workoutArtifact.domain.programme.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Programme {

  private final UUID id;
  private final LocalDateTime creationDateTime;
  private final List<Phase> phases;

  private Programme(UUID id, LocalDateTime creationDateTime, List<Phase> phases) {
    this.id = id;
    this.creationDateTime = creationDateTime;
    this.phases = phases;
  }

  public static Programme instantiate(UUID id, LocalDateTime creationDateTime, List<Phase> phases) {
    return new Programme(
        id,
        creationDateTime,
        phases
    );
  }
}

