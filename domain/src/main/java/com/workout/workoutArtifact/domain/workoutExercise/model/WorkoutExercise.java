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
  private LocalDateTime createdTime;
  private List<WorkoutSet> workoutSets;

  private WorkoutExercise(UUID id, LocalDateTime createdTime, List<WorkoutSet> workoutSets) {
    this.id = id;
    this.createdTime = createdTime;
    this.workoutSets = workoutSets;
  }

  public static WorkoutExercise createWorkoutExercise(UUID id, LocalDateTime createdTime, List<WorkoutSet> workoutSets) {
    return new WorkoutExercise(id, createdTime, workoutSets);
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
