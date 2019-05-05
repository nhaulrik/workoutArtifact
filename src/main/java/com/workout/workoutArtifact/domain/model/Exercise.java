package com.workout.workoutArtifact.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Exercise {

  @NonNull
  @JsonProperty
  private String name;

  @NonNull
  @JsonProperty
  private Boolean isMultiJoint;

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPartEnum;

  @NonNull
  @JsonProperty
  private List<Muscle> muscles;

  public String getBodyPartString() {
    return bodyPartEnum.toString();
  }

}
