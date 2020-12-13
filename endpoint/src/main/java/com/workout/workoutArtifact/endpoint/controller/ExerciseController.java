package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.request.exercise.PostExerciseRequest;
import com.workout.workoutArtifact.endpoint.request.exercise.PostExerciseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

  private final ExerciseFacade exerciseFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostExerciseResponse postExercises(@RequestBody List<PostExerciseRequest> postExerciseRequests) {
    return exerciseFacade.postExercises(postExerciseRequests);
  }


}
