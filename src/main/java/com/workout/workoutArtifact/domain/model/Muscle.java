package com.workout.workoutArtifact.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.MuscleEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Muscle {

  @NonNull
  @JsonProperty
  private MuscleEnum muscle;

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPart;

  @JsonProperty
  private List<Exercise> exerciseList = new ArrayList<>();

}
