package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.endpoint.request.workoutset.PostWorkoutSetRequest;
import com.workout.workoutArtifact.endpoint.request.workoutset.PostWorkoutSetResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/workoutset")
@RequiredArgsConstructor
public class WorkoutSetController {

  private final WorkoutSetFacade workoutSetFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostWorkoutSetResponse postWorkoutSet(@RequestBody List<PostWorkoutSetRequest> postWorkoutSetRequests) {
    return workoutSetFacade.postWorkoutSet(postWorkoutSetRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteWorkoutSet(@PathVariable UUID id) {
    return workoutSetFacade.deleteWorkoutSet(id);
  }

}
