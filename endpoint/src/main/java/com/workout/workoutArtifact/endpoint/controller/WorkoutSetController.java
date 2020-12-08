package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.endpoint.request.PostWorkoutSetRequest;
import com.workout.workoutArtifact.endpoint.request.PostWorkoutSetResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/workoutset")
@RequiredArgsConstructor
public class WorkoutSetController {

  private final WorkoutSetFacade workoutSetFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostWorkoutSetResponse postWorkoutSet(@RequestBody List<PostWorkoutSetRequest> postWorkoutSetRequests) {
    return workoutSetFacade.postWorkoutSet(postWorkoutSetRequests);
  }


}
