package com.workout.workoutArtifact.domain.session.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Session {

  private Long id;

  @NonNull
  private LocalDateTime creationDateTime;

  @NonNull
  private String location;

//  private List<WorkoutSet> workoutSets;
// TODO: 03-09-2019 at most this should be a list of workoutset ids
}
