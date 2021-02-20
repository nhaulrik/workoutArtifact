package com.workout.workoutArtifact.workoutExercise;

import com.workout.workoutArtifact.exercise.Exercise;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.workoutset.WorkoutSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;
import org.springframework.util.Assert;

@Getter
public class WorkoutExercise {

  private UUID id;
  private Integer exerciseNumber;
  private List<WorkoutSet> workoutSets;
  private Exercise exercise;
  private Boolean isWarmup;

  private UUID sessionId;

  private WorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise, Boolean isWarmup, UUID sessionId) {
    this.id = id;
    this.exerciseNumber = exerciseNumber;
    this.workoutSets = workoutSets;
    this.exercise = exercise;
    this.isWarmup = isWarmup;
    this.sessionId = sessionId;
  }

  public static WorkoutExercise createWorkoutExercise(Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise, Boolean isWarmup, UUID sessionId) {
    return new WorkoutExercise(UUID.randomUUID(), exerciseNumber, workoutSets, exercise, isWarmup, sessionId);
  }

  public static WorkoutExercise initializeWorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise, Boolean isWarmup, UUID sessionId) {
    return new WorkoutExercise(id, exerciseNumber, workoutSets, exercise, isWarmup, sessionId);
  }

  public void updateExerciseNumber(Integer exerciseNumber) {
    Assert.notNull(exerciseNumber, "exerciseNumber is required");
    this.exerciseNumber = exerciseNumber;
  }

  public Optional<WorkoutSet> getWorkoutSet(UUID id) {
    if (id == null) {
      return Optional.empty();
    }
    return this.workoutSets.stream().filter(ws -> id.equals(ws.getId())).findFirst();
  }

  public void changeExerciseNumber(Integer exerciseNumber) {
    Assert.notNull(exerciseNumber, "exerciseNumber is required");
    this.exerciseNumber = exerciseNumber;
  }

  public void addWorkoutSet(WorkoutSet workoutSet) {
    Assert.notNull(workoutSet, "workoutSet is required");

    if (this.workoutSets.stream().filter(we -> we.getId().equals(we)).findAny().isPresent()) {
      throw new RuntimeException(String.format("workoutSet with id: %s is already present on workoutExercise with id: %s", workoutSet.getId().toString(), this.id.toString()));
    }
    this.workoutSets.add(workoutSet);
  }

  public void changeExercise(Exercise newExercise) {
    Assert.notNull(newExercise, "exercise is required");
    this.exercise = newExercise;
  }

  public void changeIsWarmup(Boolean isWarmup) {
    Assert.notNull(isWarmup, "isWarmup is required");
    this.isWarmup = isWarmup;
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutExercise> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutExercise workoutExercise) {
      return ids.contains(workoutExercise.getId());
    }
  }

  @Value
  public static class SessionIdsSpecification extends AbstractSpecification<WorkoutExercise> {

    private final List<UUID> ids;

    // TODO: 08-12-2020 returning true is a hack
    @Override
    public boolean isSatisfiedBy(WorkoutExercise workoutExercise) {
      return true;
    }
  }

  @Value
  public static class ExerciseNumbersSpecification extends AbstractSpecification<WorkoutExercise> {

    private final List<Integer> exerciseNumbers;

    @Override
    public boolean isSatisfiedBy(WorkoutExercise workoutExercise) {
      return exerciseNumbers.contains(workoutExercise.getExerciseNumber());
    }
  }

}
