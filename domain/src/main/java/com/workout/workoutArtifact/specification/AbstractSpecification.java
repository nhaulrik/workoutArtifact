package com.workout.workoutArtifact.specification;

public abstract class AbstractSpecification<T> implements Specification<T> {

  public abstract boolean isSatisfiedBy(T t);

  public AbstractSpecification<T> or(AbstractSpecification<T> specification) {
    return new OrSpecification<T>(this,specification);
  }

  public AbstractSpecification<T> and(AbstractSpecification<T> specification) {
    return new AndSpecification<T>(this,specification);
  }

  public AbstractSpecification<T> not() {
    return new NotSpecification<T>(this);
  }
}
