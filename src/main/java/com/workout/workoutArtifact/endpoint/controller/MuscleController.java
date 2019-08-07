package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.NameSpecification;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MuscleController {

  private final MuscleFacade muscleFacade;

  @Autowired
  public MuscleController(MuscleFacade muscleFacade) {
    this.muscleFacade = muscleFacade;
  }

  @PostMapping(
      value = "workoutentity/addmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  // TODO: 07-08-2019 should propably be dtos as arguments
  public String addMuscles(@RequestBody List<Muscle> muscles) {
    return muscleFacade.addMuscles(muscles);
  }

  @PostMapping(
      value = "workoutentity/getmuscles",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<MuscleDto> getMuscles(@RequestBody final List<String> muscleNames) {
    return muscleFacade.getMuscles(new NameSpecification(muscleNames));
  }
}
