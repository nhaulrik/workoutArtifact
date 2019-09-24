package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Builder
@Data
public class Exercise {

  private Long id;

  @NonNull
  private String name;

  @NonNull
  private Boolean isMultiJoint;

  @NonNull
  private String bodyPart;

  @NonNull
  private List<Long> muscleIds;

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
      return bodyParts.contains(exercise.getBodyPart());
    }
  }

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<Exercise> {
    private final Long id;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return id.equals(exercise.getId());
    }
  }

  @Value
  public static class MultiJointSpecification extends AbstractSpecification<Exercise> {
    private final Boolean multiJoint;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return multiJoint.equals(exercise.getIsMultiJoint());
    }
  }

}
