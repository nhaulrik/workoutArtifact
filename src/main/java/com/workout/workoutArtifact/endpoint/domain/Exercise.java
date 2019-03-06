package com.workout.workoutArtifact.endpoint.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Exercise {

    @JsonProperty
    private String name;

    @JsonProperty
    private Boolean isMultiJoint;

}
