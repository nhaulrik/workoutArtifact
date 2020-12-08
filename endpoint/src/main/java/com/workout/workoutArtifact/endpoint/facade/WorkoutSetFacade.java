package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.endpoint.request.PostWorkoutSetRequest;
import com.workout.workoutArtifact.endpoint.request.PostWorkoutSetResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;


  public PostWorkoutSetResponse postWorkoutSet(List<PostWorkoutSetRequest> postWorkoutSetRequests) {

    List<UUID> workoutSetIds = new ArrayList<>();
    postWorkoutSetRequests.forEach(postWorkoutSetRequest -> {
      workoutSetIds.add(workoutSetService.postWorkoutSet(
          postWorkoutSetRequest.getId(),
          postWorkoutSetRequest.getSetNumber(),
          postWorkoutSetRequest.getWeight(),
          postWorkoutSetRequest.getRepetitions(),
          postWorkoutSetRequest.getRepetitionMaximum(),
          postWorkoutSetRequest.getSingle(),
          postWorkoutSetRequest.getWorkoutExerciseId()
      ));
    });
    return new PostWorkoutSetResponse(workoutSetIds);
  }

  public void deleteWorkoutSet(UUID id) {
    workoutSetService.deleteWorkoutSet(id);
  }
}
