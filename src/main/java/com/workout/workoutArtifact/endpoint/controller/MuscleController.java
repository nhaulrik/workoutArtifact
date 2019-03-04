package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MuscleController {

  private final MuscleFacade muscleFacade;

  @Autowired
  public MuscleController(MuscleFacade muscleFacade) {
    this.muscleFacade = muscleFacade;
  }

  @RequestMapping("/")
  public String welcome() {//Welcome page, non-rest
    return "Welcome to RestTemplate Example.";
  }

  @PostMapping(
      value = "/addmuscle",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String addMuscle(@RequestBody MuscleDto muscle) {
    muscleFacade.addMuscle(muscle.getName());
    return "Muscle: " + muscle.getName() + " added.";
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