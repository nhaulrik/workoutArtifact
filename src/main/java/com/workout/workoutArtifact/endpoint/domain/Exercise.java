package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.ExerciseEnum;
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
  private ExerciseEnum name;

  @NonNull
  @JsonProperty
  private Boolean isMultiJoint;

  @JsonProperty
  private List<Muscle> muscles = new ArrayList<>();

  @NonNull
  @JsonProperty
  private BodyPartEnum bodyPartEnum;

  public String getBodyPartString() {
    return bodyPartEnum.toString();
  }

}
