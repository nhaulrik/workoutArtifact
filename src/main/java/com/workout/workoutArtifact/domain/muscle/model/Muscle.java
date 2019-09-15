package com.workout.workoutArtifact.domain.muscle.model;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class Muscle {

  private Long id;

  private String name;

  private String bodyPart;

  private List<Long> exerciseIds;

  @Value
  public static class ExerciseIdsSpecification extends AbstractSpecification<Muscle> {
    private final Long exerciseId;

    @Override
    public boolean isSatisfiedBy(Muscle muscle) {
      return muscle.getExerciseIds().contains(exerciseId); // TODO: 03-09-2019 revisit contains of list
    }
  }


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
      return bodyparts.contains(muscle.getBodyPart());
    }
  }

}
