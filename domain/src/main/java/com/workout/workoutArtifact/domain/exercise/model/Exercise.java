package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Builder
@Data
public class Exercise {

  private UUID id;

  @NonNull
  private String name;

  @NonNull
  private Boolean isCompound;

  @NonNull
  private String bodyPart;

  private List<Muscle> muscles;

  public static Exercise fromId(UUID id) {
    return Exercise.builder().id(id).build();
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
      return bodyParts.contains(exercise.getBodyPart());
    }
  }

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<Exercise> {

    private final UUID id;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return id.equals(exercise.getId());
    }
  }

  @Value
  public static class IsCompoundSpecification extends AbstractSpecification<Exercise> {

    private final Boolean isCompound;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return isCompound.equals(exercise.getIsCompound());
    }
  }

}
