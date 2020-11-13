package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutSetDto {

  private Long id;

  @NonNull
  private UUID sessionId;

  @NonNull
  private ExerciseDto exerciseDto;

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
