package com.workout.workoutArtifact.domain.workoutExercise.model;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Value;
import org.springframework.util.Assert;

@Data
public class WorkoutExercise {

  private UUID id;
  private UUID sessionId;
  private Integer exerciseNumber;
  private List<WorkoutSet> workoutSets;
  private Exercise exercise;

  private WorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    this.id = id;
    this.exerciseNumber = exerciseNumber;
    this.workoutSets = workoutSets;
    this.exercise = exercise;
  }

  public static WorkoutExercise createWorkoutExercise(Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    return new WorkoutExercise(UUID.randomUUID(), exerciseNumber, workoutSets, exercise);
  }

  public static WorkoutExercise initializeWorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets, Exercise exercise) {
    return new WorkoutExercise(id, exerciseNumber, workoutSets, exercise);
  }

  public void updateExerciseNumber(Integer exerciseNumber) {
    Assert.notNull(exerciseNumber, "exerciseNumber is required");
    this.exerciseNumber = exerciseNumber;
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
