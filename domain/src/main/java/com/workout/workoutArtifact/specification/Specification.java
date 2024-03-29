package com.workout.workoutArtifact.specification;

import java.io.Serializable;

public interface Specification<T> extends Serializable {

  boolean isSatisfiedBy(T t);
}
