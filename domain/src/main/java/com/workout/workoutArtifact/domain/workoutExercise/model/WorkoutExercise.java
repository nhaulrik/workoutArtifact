package com.workout.workoutArtifact.domain.workoutExercise.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import org.springframework.util.Assert;

@Data
public class WorkoutExercise {

  private UUID id;
  @NonNull
  private UUID sessionId;
  private Integer exerciseNumber;
  private List<WorkoutSet> workoutSets;
  private Exercise exercise;

  private WorkoutExercise(UUID id, UUID sessionId, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    this.id = id;
    this.sessionId = sessionId;
    this.exerciseNumber = exerciseNumber;
    this.workoutSets = workoutSets;
    this.exercise = exercise;
  }

  public static WorkoutExercise createWorkoutExercise(UUID sessionId, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    return new WorkoutExercise(UUID.randomUUID(), sessionId, exerciseNumber, workoutSets, exercise);
  }

  public static WorkoutExercise initializeWorkoutExercise(UUID id, UUID sessionId, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    return new WorkoutExercise(id, sessionId, exerciseNumber, workoutSets, exercise);
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

    @Override
    public boolean isSatisfiedBy(WorkoutExercise workoutExercise) {
      return ids.contains(workoutExercise.getSessionId());
    }
  }
}
