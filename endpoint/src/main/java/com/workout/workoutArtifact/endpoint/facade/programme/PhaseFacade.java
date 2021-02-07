package com.workout.workoutArtifact.endpoint.facade.programme;

import com.workout.workoutArtifact.domain.programme.service.PhaseService;
import com.workout.workoutArtifact.endpoint.request.programme.PostPhaseRequest;
import com.workout.workoutArtifact.endpoint.request.programme.PostPhaseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhaseFacade {

  private final PhaseService phaseService;


  public PostPhaseResponse postPhases(List<PostPhaseRequest> postPhaseRequests) {
    List<UUID> phaseIds = new ArrayList<>();
    postPhaseRequests.forEach(postPhaseRequest -> {
      phaseIds.add(phaseService.postPhase(
          postPhaseRequest.getId(),
          postPhaseRequest.getName(),
          postPhaseRequest.getDescription(),
          postPhaseRequest.getNumber(),
          postPhaseRequest.getProgrammeId(),
          postPhaseRequest.getSplitIds()
      ));
    });
    return new PostPhaseResponse(phaseIds);
  }

  public Boolean deletePhase(UUID id) {
    return phaseService.deletePhase(id);
  }
}
