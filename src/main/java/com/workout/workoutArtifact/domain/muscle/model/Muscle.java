package com.workout.workoutArtifact.domain.muscle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@Builder
public class Muscle {

  @NonNull
  @JsonProperty
  private String name;

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPart;

  @JsonProperty
  private List<Exercise> exerciseList = new ArrayList<>();

  @Value
  public static class NameSpecification extends AbstractSpecification<Muscle> {
    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(Muscle muscle) {
      return names.contains(muscle.getName());
    }
  }

  @Value
  public static class BodyPartSpecification extends AbstractSpecification<Muscle> {
    private final List<String> bodyparts;

    @Override
    public boolean isSatisfiedBy(Muscle muscle) {
      return bodyparts.contains(muscle.getBodyPart().name());
    }
  }

}
