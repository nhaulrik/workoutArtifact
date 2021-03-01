package com.workout.workoutArtifact.specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder {

  List<AbstractSpecification> specifications = new ArrayList<>();

  public SpecificationBuilder withSpecification(AbstractSpecification abstractSpecification) {
    this.specifications.add(abstractSpecification);
    return this;
  }

  public AbstractSpecification build() {
    return this.specifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());
  }
}
