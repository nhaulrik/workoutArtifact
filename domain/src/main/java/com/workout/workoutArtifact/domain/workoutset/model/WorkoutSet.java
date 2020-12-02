package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Value;
import org.springframework.util.Assert;


@Data
public class WorkoutSet {

  private UUID id;
  private Boolean single;
  private Double weight;
  private Integer repetitions;
  private Integer repetitionMaximum;
  private Integer setNumber;

  private WorkoutSet(UUID id, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    this.id = id;
    this.single = single;
    this.weight = weight;
    this.repetitions = repetitions;
    this.repetitionMaximum = repetitionMaximum;
    this.setNumber = setNumber;
  }

  public static WorkoutSet initializeWorkoutSet(UUID workoutSetId, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    return new WorkoutSet(
        workoutSetId, single, weight, repetitions, repetitionMaximum, setNumber);
  }

  public static WorkoutSet createWorkoutSet(Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    return new WorkoutSet(
        UUID.randomUUID(), single, weight, repetitions, repetitionMaximum, setNumber);
  }

  public static WorkoutSet fromId(UUID id) {
    return new WorkoutSet(id, null, null, null, null, null);
  }

  public void changeSetNumber(Integer setNumber) {
    Assert.notNull(setNumber, "setNumber is required");
    this.setNumber = setNumber;
  }

  public void changeSetNumber(Double weight) {
    Assert.notNull(weight, "weight is not required");
    this.weight = weight;
  }

  public void changeRepetitions(Integer repetitions) {
    Assert.notNull(repetitions, "repetitions is required");
    this.repetitions = repetitions;
  }

  public void changeRepetitionMaximum(Integer repetitionMaximum) {
    Assert.notNull(repetitionMaximum, "repetitionMaximum is required");
    this.repetitionMaximum = repetitionMaximum;
  }

  public void changeIsSingle(Boolean single) {
    Assert.notNull(single, "single is required");
    this.single = single;
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
