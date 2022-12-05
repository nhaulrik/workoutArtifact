package com.workout.workoutArtifact.user;

import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StaticUsers {

  NIKOLAJ(UUID.fromString("51a649d4-d693-4b69-b039-b5ed0f971ac7"));

  public final UUID id;

  }
