package com.workout.workoutArtifact.facade;

import com.workout.workoutArtifact.application.WorkoutApplicationService;
import com.workout.workoutArtifact.workoutset.WorkoutSetService;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetRequest;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutSetFacade {

  private final WorkoutSetService workoutSetService;
  private final WorkoutApplicationService workoutApplicationService;

  public PostWorkoutSetResponse postWorkoutSet(List<PostWorkoutSetRequest> postWorkoutSetRequests) {

    List<UUID> workoutSetIds = new ArrayList<>();
    postWorkoutSetRequests.forEach(postWorkoutSetRequest -> {
      workoutSetIds.add(workoutApplicationService.handleWorkoutSet(
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

  public Boolean deleteWorkoutSet(UUID id) {
    return workoutSetService.deleteWorkoutSet(id);
  }
}
