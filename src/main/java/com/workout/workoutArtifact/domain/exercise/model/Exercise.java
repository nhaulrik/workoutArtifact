package com.workout.workoutArtifact.domain.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Exercise {

  @NonNull
  @JsonProperty
  private String name;

  @NonNull
  @JsonProperty
  private Boolean isMultiJoint;

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPartEnum;

  public String getBodyPartString() {
    return bodyPartEnum.toString();
  }

  @Value
  public static class NameSpecification extends AbstractSpecification<Exercise> {
    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return names.contains(exercise.getName());
    }
  }

  @Value
  public static class BodyPartsSpecification extends AbstractSpecification<Exercise> {
    private final List<String> bodyParts;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return bodyParts.contains(exercise.getBodyPartEnum().name());
    }
  }


}
