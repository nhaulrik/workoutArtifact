package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.time.LocalDateTime;
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
public class WorkoutExerciseDto {

  @NonNull
  private UUID id;
  private LocalDateTime createdTime;
  private List<UUID> workoutSetIds;

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutExerciseDto> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutExerciseDto workoutExerciseDto) {
      return ids.contains(workoutExerciseDto.getId());
    }
  }


}
