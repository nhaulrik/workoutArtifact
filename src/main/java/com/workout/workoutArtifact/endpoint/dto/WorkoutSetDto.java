package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@Builder
public class WorkoutSetDto {

  private Long id;

  @NonNull
  private Long exerciseId;

  @NonNull
  private int repetitions;

  @NonNull
  private double weight;

  @NonNull
  private boolean single;

  @NonNull
  private int repetitionMaximum;

  @NonNull
  private int setNumber;

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutSetDto> {

    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSetDto workoutSetDto) {
      return ids.contains(workoutSetDto.getId());
    }
  }

}
