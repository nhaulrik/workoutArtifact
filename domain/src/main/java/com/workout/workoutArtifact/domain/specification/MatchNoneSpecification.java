package com.workout.workoutArtifact.domain.specification;

import lombok.Value;

@Value
public class MatchNoneSpecification<T> extends AbstractSpecification<T> {

  @Override
  public boolean isSatisfiedBy(T o) {
    return false;
  }
}
