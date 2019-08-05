package com.workout.workoutArtifact.specification;

import lombok.Value;

@Value
public class MatchAllSpecification extends AbstractSpecification {
  @Override
  public boolean isSatisfiedBy(Object o) {
    return true;
  }
}
