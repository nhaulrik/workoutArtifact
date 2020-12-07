package com.workout.workoutArtifact.domain.specification;

import java.util.HashSet;
import java.util.Set;
import lombok.Value;

@Value
public class AndSpecification<T> extends AbstractSpecification<T> {

  private Set<AbstractSpecification<T>> set = new HashSet<>();

  public AndSpecification(AbstractSpecification<T> a, AbstractSpecification<T> b) {
    set.add(a);
    set.add(b);
  }

  public boolean isSatisfiedBy(T t) {
    return set.stream().allMatch(s -> s.isSatisfiedBy(t));
  }

  @Override
  public AbstractSpecification<T> and(AbstractSpecification<T> s) {
    set.add(s);
    return this;
  }

}
