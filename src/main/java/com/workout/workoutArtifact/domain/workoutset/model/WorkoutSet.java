package com.workout.workoutArtifact.domain.workoutset.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;


@Data
@Builder
public class WorkoutSet {

  @JsonProperty
  private Long id;

  @JsonProperty
  @NonNull
  private String exerciseName;

  @JsonProperty
  @NonNull
  private int repetitions;

  @JsonProperty
  @NonNull
  private double weight;

  @JsonProperty
  @NonNull
  private Boolean single;

  @JsonProperty
  @NonNull
  private int repetitionMaximum;

  @JsonProperty
  @NonNull
  private Exercise exercise;

  @JsonProperty
  @NonNull
  private int setNumber;

  @Value
  public static class ExerciseNameSpecification extends AbstractSpecification<WorkoutSet> {
    private final List<String> exerciseNames;

    @Override
    public boolean isSatisfiedBy(WorkoutSet workoutSet) {
      return exerciseNames.contains(workoutSet.getExerciseName());
    }
  }

}
