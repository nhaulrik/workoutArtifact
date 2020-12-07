package com.workout.workoutArtifact.graphql.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutExerciseDto {

  @NonNull
  private UUID id;
  private UUID sessionId;
  private UUID exerciseId;
  private Integer exerciseNumber;

}
