package com.workout.workoutArtifact.workoutset;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;


@Getter
public class WorkoutSet {

  private UUID id;
  private UUID workoutExerciseId;
  private Boolean single;
  private Double weight;
  private Integer repetitions;
  private Integer repetitionMaximum;
  private Integer setNumber;

  private WorkoutSet(UUID id, UUID workoutExerciseId, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    this.id = id;
    this.single = single;
    this.weight = weight;
    this.repetitions = repetitions;
    this.repetitionMaximum = repetitionMaximum;
    this.setNumber = setNumber;
    this.workoutExerciseId = workoutExerciseId;
  }

  public Double getTotalWeight() {
    return repetitions * weight;
  }

  public static WorkoutSet initializeWorkoutSet(UUID workoutSetId, UUID workoutExerciseId, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    return new WorkoutSet(
        workoutSetId, workoutExerciseId, single, weight, repetitions, repetitionMaximum, setNumber);
  }

  public static WorkoutSet createWorkoutSet(UUID workoutExerciseId, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    return new WorkoutSet(
        UUID.randomUUID(), workoutExerciseId, single, weight, repetitions, repetitionMaximum, setNumber);
  }

  public void changeSetNumber(Integer setNumber) {
    if (setNumber != null) {
      this.setNumber = setNumber;
    }
  }

  public void changeWeight(Double weight) {
    if (weight != null) {
      this.weight = weight;
    }
  }

  public void changeRepetitions(Integer repetitions) {
    if (repetitions != null) {
      this.repetitions = repetitions;
    }
  }

  public void changeRepetitionMaximum(Integer repetitionMaximum) {
    if (repetitionMaximum != null) {
      this.repetitionMaximum = repetitionMaximum;
    }
  }

  public void changeIsSingle(Boolean single) {
    if (single != null) {
      this.single = single;
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

  @Value
  public static class WorkoutExerciseIdsSpecification extends AbstractSpecification<WorkoutSet> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return ids.contains(workoutSet.getWorkoutExerciseId());
    }
  }

}
