package com.workout.workoutArtifact.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Session {

  @JsonProperty
  private Long id;

  @NonNull
  @JsonProperty
  private LocalDateTime creationDateTime;

  @NonNull
  @JsonProperty
  private String location;

  @JsonProperty
  private List<WorkoutSet> workoutSets;

}
