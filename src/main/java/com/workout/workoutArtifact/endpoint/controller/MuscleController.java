package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import org.springframework.beans.factory.annotation.Autowired;
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

//  @GetMapping(
//      value = "muscle/{muscleName}",
//      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//  )
//  public List<Muscle> getMusclesByName(
//      @PathVariable("muscleName") final String muscleName) {
//    return muscleFacade.getMuscleByName(muscleName);
//  }
}