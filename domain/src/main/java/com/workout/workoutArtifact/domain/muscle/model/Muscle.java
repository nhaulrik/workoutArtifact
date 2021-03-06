package com.workout.workoutArtifact.domain.muscle.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data // TODO: 16-09-2019 change to Value
@Builder
public class Muscle {

  private UUID id;

  private String name;

  private String bodyPart;

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
