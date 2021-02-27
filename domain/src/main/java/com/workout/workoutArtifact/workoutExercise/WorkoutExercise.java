package com.workout.workoutArtifact.workoutExercise;

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
  private UUID exerciseId;
  private Boolean isWarmup;

  private UUID sessionId;

  private WorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, UUID exerciseId, Boolean isWarmup, UUID sessionId) {
    this.id = id;
    this.exerciseNumber = exerciseNumber;
    this.workoutSets = workoutSets;
    this.exerciseId = exerciseId;
    this.isWarmup = isWarmup;
    this.sessionId = sessionId;
  }

  public static WorkoutExercise createWorkoutExercise(Integer exerciseNumber, List<WorkoutSet> workoutSets, UUID exerciseId, Boolean isWarmup, UUID sessionId) {
    return new WorkoutExercise(UUID.randomUUID(), exerciseNumber, workoutSets, exerciseId, isWarmup, sessionId);
  }

  public static WorkoutExercise initializeWorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, UUID exerciseId, Boolean isWarmup, UUID sessionId) {
    return new WorkoutExercise(id, exerciseNumber, workoutSets, exerciseId, isWarmup, sessionId);
  }

  public Double getAverage() {

    Integer totalRepetitions = workoutSets.stream()
        .map(WorkoutSet::getRepetitions)
        .reduce(0, Integer::sum);

    Double totalWeight = workoutSets.stream()
        .map(WorkoutSet::getTotalWeight)
        .reduce(0d, Double::sum);

    Double workoutExerciseAverageWeight = totalWeight / totalRepetitions;
    return workoutExerciseAverageWeight;
  }

  public void updateExerciseNumber(Integer exerciseNumber) {
    Assert.notNull(exerciseNumber, "exerciseNumber is required");
    this.exerciseNumber = exerciseNumber;
  }

  public void changeExerciseNumber(Integer exerciseNumber) {
    Assert.notNull(exerciseNumber, "exerciseNumber is required");
    this.exerciseNumber = exerciseNumber;
  }

  public void changeExercise(UUID newExerciseId) {
    Assert.notNull(newExerciseId, "exercise Id is required");
    this.exerciseId = newExerciseId;
  }

  public void changeIsWarmup(Boolean isWarmup) {
    Assert.notNull(isWarmup, "isWarmup is required");
    this.isWarmup = isWarmup;
  }

  public Optional<WorkoutSet> getWorkoutSet(UUID id) {
    if (id == null) {
      return Optional.empty();
    }
    return this.workoutSets.stream().filter(ws -> id.equals(ws.getId())).findFirst();
  }

  public void addWorkoutSet(WorkoutSet workoutSet) {
    Assert.notNull(workoutSet, "workoutSet is required");

    if (this.workoutSets.stream().filter(we -> we.getId().equals(we)).findAny().isPresent()) {
      throw new RuntimeException(String.format("workoutSet with id: %s is already present on workoutExercise with id: %s", workoutSet.getId().toString(), this.id.toString()));
    }
    this.workoutSets.add(workoutSet);
  }

  public void removeWorkoutset(UUID id) {
    this.workoutSets.removeIf(workoutSet -> workoutSet.getId().equals(id));
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