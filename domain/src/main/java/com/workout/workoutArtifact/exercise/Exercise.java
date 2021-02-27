package com.workout.workoutArtifact.exercise;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import org.springframework.util.Assert;

@Data
public class Exercise {

  private UUID id;

  private LocalDateTime createDate;

  @NonNull
  private String name;

  @NonNull
  private Boolean isCompound;

  @NonNull
  private String bodyPart;

  private final List<UUID> muscleIds;

  private Exercise(UUID id, LocalDateTime createDate, @NonNull String name, @NonNull Boolean isCompound, @NonNull String bodyPart, List<UUID> muscleIds) {
    this.id = id;
    this.createDate = createDate;
    this.name = name;
    this.isCompound = isCompound;
    this.bodyPart = bodyPart;
    this.muscleIds = muscleIds;
  }

  public static Exercise createExercise(@NonNull String name, @NonNull Boolean isCompound, @NonNull String bodyPart) {
    return new Exercise(UUID.randomUUID(), LocalDateTime.now(), name, isCompound, bodyPart, new ArrayList<>());
  }

  public static Exercise instantiate(
      UUID id,
      String name,
      Boolean isCompound,
      String bodyPart,
      LocalDateTime createDate,
      List<UUID> muscleIds
  ) {
    return new Exercise(
        id,
        createDate,
        name,
        isCompound,
        bodyPart,
        muscleIds
    );
  }

  public void changeName(String name) {
    Assert.notNull(name, "name is required");
    this.name = name;
  }

  public void changeBodyPart(String bodyPart) {
    Assert.notNull(bodyPart, "bodyPart is required");
    this.bodyPart = bodyPart;
  }


  public void changeIsCompound(Boolean isCompound) {
    Assert.notNull(isCompound, "isCompound is required");
    this.isCompound = isCompound;
  }

  public void removeMuscle(UUID muscleId) {
    this.muscleIds.removeIf(id -> id.equals(muscleId));
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
  public static class ExerciseIdsSpecification extends AbstractSpecification<Exercise> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(Exercise exercise) {
      return ids.contains(exercise.getId());
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
