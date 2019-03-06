package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @RequestMapping("/")
  public String welcome() {//Welcome page, non-rest
    return "Welcome to RestTemplate Example.";
  }

  @PostMapping(
      value = "workoutentity/addmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addMuscles(@RequestBody ArrayList<Muscle> muscles) {
    return muscleFacade.addMuscles(muscles);
  }

  @PostMapping(
      value = "workoutentity/getmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<Muscle> getMuscles(@RequestBody final List<String> muscleNames) {
    return muscleFacade.getMusclesByName(muscleNames);
  }

  @PostMapping(
      value = "workoutentity/addexercise",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addExercise(@RequestBody Exercise exercise) {
    exerciseFacade.addExercise(exercise.getName(), exercise.getIsMultiJoint());
    return "Exercise: '" + exercise.getName() + "' added.";
  }

//  @GetMapping(
//      value = "muscle/{muscleName}",
//      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//  )
//  public List<Muscle> getMusclesByName(
//      @PathVariable("muscleName") final String muscleName) {
//    return muscleFacade.getMuscleByName(muscleName);
//  }
}