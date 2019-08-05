package com.workout.workoutArtifact.specification;

import lombok.Value;

@Value
public class MatchNoneSpecification extends AbstractSpecification {

  @Override
  public boolean isSatisfiedBy(Object o) {
    return false;
  }
}
