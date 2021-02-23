package com.workout.workoutArtifact.controller;

import com.workout.workoutArtifact.facade.WorkoutExerciseFacade;
import com.workout.workoutArtifact.request.workoutexercise.PostWorkoutExerciseRequest;
import com.workout.workoutArtifact.request.workoutexercise.PostWorkoutExerciseResponse;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetRequest;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetResponse;
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
@RequestMapping("/api/v1/workoutexercise")
@RequiredArgsConstructor
public class WorkoutExerciseController {

  private final WorkoutExerciseFacade workoutExerciseFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostWorkoutExerciseResponse postWorkoutExercises(@RequestBody List<PostWorkoutExerciseRequest> postWorkoutExerciseRequests) {
    return workoutExerciseFacade.postWorkoutExercises(postWorkoutExerciseRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteWorkoutExercise(@PathVariable UUID id) {
    return workoutExerciseFacade.deleteWorkoutExercise(id);
  }

  @DeleteMapping("/{id}/workoutset/{workoutsetId}")
  public Boolean deleteWorkoutSet(@PathVariable UUID id, @PathVariable UUID workoutsetId) {
    return workoutExerciseFacade.deleteWorkoutSet(id, workoutsetId);
  }

  @PostMapping(path = "/{id}/workoutset", consumes = "application/json", produces = "application/json")
  public PostWorkoutSetResponse postWorkoutSet(@RequestBody List<PostWorkoutSetRequest> postWorkoutSetRequests) {
    return workoutExerciseFacade.postWorkoutSet(postWorkoutSetRequests);
  }


}
