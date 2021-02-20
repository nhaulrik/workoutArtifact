package com.workout.workoutArtifact.controller;

import com.workout.workoutArtifact.facade.ExerciseFacade;
import com.workout.workoutArtifact.request.exercise.PostExerciseRequest;
import com.workout.workoutArtifact.request.exercise.PostExerciseResponse;
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
@RequestMapping("/api/v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

  private final ExerciseFacade exerciseFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostExerciseResponse postExercises(@RequestBody List<PostExerciseRequest> postExerciseRequests) {
    return exerciseFacade.postExercises(postExerciseRequests);
  }

  @DeleteMapping("/{exerciseId}/{muscleId}")
  public Boolean deleteMuscleFromExercise(@PathVariable UUID exerciseId, @PathVariable UUID muscleId) {
    return exerciseFacade.deleteMuscleFromExercise(exerciseId, muscleId);
  }

  @DeleteMapping("/{exerciseId}")
  public Boolean deleteExercise(@PathVariable UUID exerciseId) {
    return exerciseFacade.deleteExercise(exerciseId);
  }

}
