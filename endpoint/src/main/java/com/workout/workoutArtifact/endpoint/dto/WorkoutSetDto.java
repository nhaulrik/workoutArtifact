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

  @NonNull
  private UUID id;

  @NonNull
  private UUID sessionId;

  private UUID exerciseId;

  private Integer repetitions;

  private Double weight;

  private Boolean single;

  private Integer repetitionMaximum;

  private Integer setNumber;

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutSetDto> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSetDto workoutSetDto) {
      return ids.contains(workoutSetDto.getId());
    }
  }

  @Value
  public static class SessionIdsSpecification extends AbstractSpecification<WorkoutSetDto> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutSetDto workoutSetDto) {
      return ids.contains(workoutSetDto.getSessionId());
    }
  }

}
