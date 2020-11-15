package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Value;


@Data
@Builder
public class WorkoutSet {

  private UUID id;
  private UUID sessionId;
  private Exercise exercise;
  private Boolean single;
  private double weight;
  private int repetitions;
  private int repetitionMaximum;
  private int setNumber;

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<UUID> exerciseIds;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return exerciseIds.contains(workoutSet.getExercise().getId());
    }
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return ids.contains(workoutSet.getId());
    }
  }

}
