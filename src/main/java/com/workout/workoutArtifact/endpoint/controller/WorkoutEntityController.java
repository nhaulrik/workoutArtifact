package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutEntityController {

  private final MuscleFacade muscleFacade;
  private final ExerciseFacade exerciseFacade;

  @Autowired
  public WorkoutEntityController(
      MuscleFacade muscleFacade,
      ExerciseFacade exerciseFacade) {
    this.muscleFacade = muscleFacade;
    this.exerciseFacade = exerciseFacade;
  }

  @PostMapping(
      value = "workoutentity/addmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addMuscles(@RequestBody List<Muscle> muscles) {
    return muscleFacade.addMuscles(muscles);
  }

  @PostMapping(
      value = "workoutentity/getmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<MuscleDto> getMuscles(@RequestBody final List<String> muscleNames) {
    return muscleFacade.getMusclesByName(muscleNames);
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
    return exerciseFacade.getExercises(exerciseNames);
  }
}