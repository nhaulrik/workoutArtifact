package com.workout.workoutArtifact.domain.specification;

import java.io.Serializable;

public interface Specification<T> extends Serializable {

  boolean isSatisfiedBy(T t);
}
