package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;


@Data
@Builder
public class WorkoutSet {

  private Long id;

  @NonNull
  private int repetitions;

  @NonNull
  private double weight;

  @NonNull
  private Boolean single;

  @NonNull
  private int repetitionMaximum;

  @NonNull
  private Long exerciseId;

  @NonNull
  private int setNumber;

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<Long> exerciseIds;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return exerciseIds.contains(workoutSet.getId());
    }
  }

}
