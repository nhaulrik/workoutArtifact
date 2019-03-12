package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workout.workoutArtifact.common.ExerciseEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exercise {

    @NonNull
    @JsonProperty
    private ExerciseEnum name;

    @JsonProperty
    private Boolean isMultiJoint;

    @JsonProperty
    private List<Muscle> muscles;

}
