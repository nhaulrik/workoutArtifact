package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Builder
@Data
public class Exercise {

  @NonNull
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private Boolean isMultiJoint;

  @NonNull
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
