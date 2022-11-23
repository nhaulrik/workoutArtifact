package com.workout.workoutArtifact.request.programme;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostProgrammeResponse {

  private final List<UUID> postedProgrammeIds;


}
