package com.workout.workoutArtifact.facade.programme;

import com.workout.workoutArtifact.programme.ProgrammeService;
import com.workout.workoutArtifact.request.programme.PostProgrammeRequest;
import com.workout.workoutArtifact.request.programme.PostProgrammeResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgrammeFacade {

  private final ProgrammeService programmeService;

  public PostProgrammeResponse postProgrammes(List<PostProgrammeRequest> postProgrammeRequests) {
    List<UUID> programmeIds = new ArrayList<>();
    postProgrammeRequests.forEach(postProgrammeRequest -> {
      programmeIds.add(programmeService.postProgramme(
          postProgrammeRequest.getId(),
          postProgrammeRequest.getName(),
          postProgrammeRequest.getDescription(),
          postProgrammeRequest.getDate(),
          postProgrammeRequest.getPhaseIds()
      ));
    });
    return new PostProgrammeResponse(programmeIds);
  }

  public Boolean deleteProgramme(UUID id) {
    return programmeService.deleteProgramme(id);
  }
}
