package com.workout.workoutArtifact.domain.workoutExercise.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Value;

@Data
public class WorkoutExercise {

  private UUID id;
  private UUID sessionId;
  private Integer exerciseNumber;
  private List<WorkoutSet> workoutSets;

  private WorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets) {
    this.id = id;
    this.exerciseNumber = exerciseNumber;
    this.workoutSets = workoutSets;
  }

  public static WorkoutExercise createWorkoutExercise(UUID id, Integer exerciseNumber, List<WorkoutSet> workoutSets) {
    return new WorkoutExercise(id, exerciseNumber, workoutSets);
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
