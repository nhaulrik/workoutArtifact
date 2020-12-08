package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.WorkoutExerciseFacade;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.CreateWorkoutExerciseRequest;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.CreateWorkoutExerciseResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/workoutexercise")
@RequiredArgsConstructor
public class WorkoutExerciseController {

  private final WorkoutExerciseFacade workoutExerciseFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public CreateWorkoutExerciseResponse createWorkoutExercises(@RequestBody List<CreateWorkoutExerciseRequest> postWorkoutExerciseRequests) {
    return workoutExerciseFacade.createWorkoutExercises(postWorkoutExerciseRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseFacade.deleteWorkoutExercise(id);
  }

}
