package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      value = "workoutentity/addmuscle",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addMuscle(@RequestBody MuscleDto muscleDto) {
    muscleFacade.addMuscle(muscleDto.getName());
    return "Muscle: '" + muscleDto.getName() + "' added.";
  }

  @PostMapping(
      value = "workoutentity/getmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<MuscleDto> getMuscles(@RequestBody final List<String> muscleNames) {
    return muscleFacade.getMusclesByName(muscleNames);
  }

  @PostMapping(
      value = "workoutentity/addexercise",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addExercise(@RequestBody ExerciseDto exerciseDto) {
    exerciseFacade.addExercise(exerciseDto.getName(), exerciseDto.getIsMultiJoint());
    return "Exercise: '" + exerciseDto.getName() + "' added.";
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