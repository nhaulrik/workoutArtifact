package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;


@Data
@Builder
public class WorkoutSet {

  private Long id;
  private Long sessionId;
  private Long exerciseId;
  private Boolean single;
  private double weight;
  private int repetitions;
  private int repetitionMaximum;
  private int setNumber;

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<Long> exerciseIds;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return exerciseIds.contains(workoutSet.getExerciseId());
    }
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return ids.contains(workoutSet.getId());
    }
  }

}
