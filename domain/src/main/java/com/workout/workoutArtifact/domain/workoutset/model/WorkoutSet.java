package com.workout.workoutArtifact.domain.workoutset.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.util.Assert;


@Data
public class WorkoutSet {

  private UUID id;
  private UUID sessionId;
  private Exercise exercise;
  private Boolean single;
  private Double weight;
  private Integer repetitions;
  private Integer repetitionMaximum;
  private Integer setNumber;
  private LocalDateTime createdTime;

  private WorkoutSet(UUID id, UUID sessionId, Exercise exercise, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber, LocalDateTime createdTime) {
    this.id = id;
    this.sessionId = sessionId;
    this.exercise = exercise;
    this.single = single;
    this.weight = weight;
    this.repetitions = repetitions;
    this.repetitionMaximum = repetitionMaximum;
    this.setNumber = setNumber;
    this.createdTime = createdTime;
  }

  public static WorkoutSet initializeWorkoutSet(UUID workoutSetId, UUID sessionId, Exercise exercise, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber, LocalDateTime createdTime) {
    return new WorkoutSet(
        workoutSetId, sessionId, exercise, single, weight, repetitions, repetitionMaximum, setNumber, createdTime);
  }

  public static WorkoutSet createWorkoutSet(UUID sessionId, Exercise exercise, Boolean single, Double weight, Integer repetitions, Integer repetitionMaximum, Integer setNumber) {
    return new WorkoutSet(
        UUID.randomUUID(), sessionId, exercise, single, weight, repetitions, repetitionMaximum, setNumber, LocalDateTime.now());
  }

  public static WorkoutSet fromId(UUID id) {
    return new WorkoutSet(id, null, null,null,null,null,null, null, null);
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

  public void changeExercise(Exercise exercise) {
    Assert.notNull(exercise, "exercise is required");
    this.exercise = exercise;
  }

  public void changeSession(UUID sessionId) {
    Assert.notNull(sessionId, "sessionId is required");
    this.sessionId = sessionId;
  }

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

  @Value
  public static class SessionIdsSpecification extends AbstractSpecification<WorkoutSet> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return ids.contains(workoutSet.getSessionId());
    }
  }

}
