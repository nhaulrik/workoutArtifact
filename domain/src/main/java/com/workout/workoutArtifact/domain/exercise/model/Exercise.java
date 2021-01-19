package com.workout.workoutArtifact.domain.exercise.model;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  private List<Muscle> muscles;

  private Exercise(UUID id, LocalDateTime createDate, @NonNull String name, @NonNull Boolean isCompound, @NonNull String bodyPart, List<Muscle> muscles) {
    this.id = id;
    this.createDate = createDate;
    this.name = name;
    this.isCompound = isCompound;
    this.bodyPart = bodyPart;
    this.muscles = muscles;
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
      List<Muscle> muscles
  ) {
    Exercise exercise = new Exercise(
        id,
        createDate,
        name,
        isCompound,
        bodyPart,
        muscles
    );
    return exercise;
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

  public Optional<Muscle> getMuscle(UUID muscleId) {
    if (muscleId == null) {
      return Optional.empty();
    }
    return this.muscles.stream().filter(m -> muscleId.equals(m.getId())).findFirst();
  }

  public void addMuscle(Muscle muscle) {
    Assert.notNull(muscle, "Muscle is required");
    Assert.isTrue(!this.getMuscle(muscle.getId()).isPresent(), String.format("muscle already exist on exercise with id: %s", this.id));

    this.muscles.add(muscle);
  }

  public void removeMuscle(UUID muscleId) {
    Assert.notNull(muscleId, "muscleId is required");
    this.muscles.removeIf(muscle -> muscle.getId().equals(muscleId));
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
