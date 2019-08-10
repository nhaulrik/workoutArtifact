package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

  private final ExerciseFacade exerciseFacade;

  @Autowired
  public ExerciseController(
      ExerciseFacade exerciseFacade) {
    this.exerciseFacade = exerciseFacade;
  }

  @PostMapping(
      value = "workoutentity/addexercises",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addExercise(@RequestBody List<Exercise> exercises) {
    return exerciseFacade.addExercises(exercises);
  }

  @PostMapping(
      value = "workoutentity/getexercises",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<ExerciseDto> getExercises(@RequestBody final List<String> exerciseNames) {
    return exerciseFacade.getExercises(new NameSpecification(exerciseNames));
  }
}