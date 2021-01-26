package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.ProgrammeFacade;
import com.workout.workoutArtifact.endpoint.request.programme.PostProgrammeRequest;
import com.workout.workoutArtifact.endpoint.request.programme.PostProgrammeResponse;
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
@RequestMapping("/api/v1/programme")
@RequiredArgsConstructor
public class ProgrammeController {

  private final ProgrammeFacade programmeFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostProgrammeResponse postProgrammes(@RequestBody List<PostProgrammeRequest> postProgrammeRequests) {
    return programmeFacade.postProgrammes(postProgrammeRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deleteProgramme(@PathVariable UUID id) {
    return programmeFacade.deleteProgramme(id);
  }


}
