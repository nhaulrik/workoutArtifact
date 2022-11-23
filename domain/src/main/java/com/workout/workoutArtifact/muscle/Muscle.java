package com.workout.workoutArtifact.muscle;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;

@Getter
public class Muscle {

  private UUID id;

  private String name;

  private String bodyPart;

  private Muscle(UUID id, String name, String bodyPart) {
    this.id = id;
    this.name = name;
    this.bodyPart = bodyPart;
  }

  public static Muscle createMuscle(String name, String bodyPart) {
    return new Muscle(UUID.randomUUID(), name, bodyPart);
  }

  public static Muscle instantiate(UUID id, String name, String bodyPart) {
    return new Muscle(id, name, bodyPart);
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<Muscle> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(Muscle muscle) {
      return ids.contains(muscle.getId());
    }
  }

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
      return bodyparts.contains(muscle.getBodyPart());
    }
  }

}
