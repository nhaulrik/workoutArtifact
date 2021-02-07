package com.workout.workoutArtifact.endpoint.controller.programme;

import com.workout.workoutArtifact.endpoint.facade.programme.PhaseFacade;
import com.workout.workoutArtifact.endpoint.request.programme.PostPhaseRequest;
import com.workout.workoutArtifact.endpoint.request.programme.PostPhaseResponse;
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
@RequestMapping("/api/v1/phase")
@RequiredArgsConstructor
public class PhaseController {

  private final PhaseFacade phaseFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostPhaseResponse postPhase(@RequestBody List<PostPhaseRequest> postPhaseRequests) {
    return phaseFacade.postPhases(postPhaseRequests);
  }

  @DeleteMapping("/{id}")
  public Boolean deletePhase(@PathVariable UUID id) {
    return phaseFacade.deletePhase(id);
  }

}
