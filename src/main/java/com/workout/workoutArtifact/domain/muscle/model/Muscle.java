package com.workout.workoutArtifact.domain.muscle.model;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@Builder
public class Muscle {

  @NonNull
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private BodyPartEnum bodyPart;

  // TODO: 03-09-2019   it is fine to have a list of exerciseIds instead of a list of Exercise.


  @Value
  public static class IdsSpecification extends AbstractSpecification<Muscle> {
    private final List<Long> ids;

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
      return bodyparts.contains(muscle.getBodyPart().name());
    }
  }

}
