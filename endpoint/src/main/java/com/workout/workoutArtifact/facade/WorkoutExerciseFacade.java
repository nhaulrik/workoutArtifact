package com.workout.workoutArtifact.facade;

import com.workout.workoutArtifact.application.workoutexercise.DeleteWorkoutSet;
import com.workout.workoutArtifact.application.workoutexercise.PostWorkoutSet;
import com.workout.workoutArtifact.application.WorkoutExerciseApplicationService;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetRequest;
import com.workout.workoutArtifact.request.workoutset.PostWorkoutSetResponse;
import com.workout.workoutArtifact.workoutExercise.WorkoutExerciseService;
import com.workout.workoutArtifact.request.workoutexercise.PostWorkoutExerciseRequest;
import com.workout.workoutArtifact.request.workoutexercise.PostWorkoutExerciseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFacade {

  private final WorkoutExerciseService workoutExerciseService;
  private final WorkoutExerciseApplicationService workoutExerciseApplicationService;

  private final PostWorkoutSet postWorkoutSet;
  private final DeleteWorkoutSet deleteWorkoutSet;

  public PostWorkoutExerciseResponse postWorkoutExercises(List<PostWorkoutExerciseRequest> createWorkoutExerciseRequests) {

    List<UUID> workoutExerciseIds = new ArrayList<>();
    createWorkoutExerciseRequests.forEach(postWorkoutExerciseRequest -> {
      workoutExerciseIds.add(workoutExerciseApplicationService.handleWorkoutExercise(
          postWorkoutExerciseRequest.getId(),
          postWorkoutExerciseRequest.getExerciseId(),
          postWorkoutExerciseRequest.getExerciseNumber(),
          postWorkoutExerciseRequest.getSessionId(),
          postWorkoutExerciseRequest.getIsWarmup()
      ));
    });
    return new PostWorkoutExerciseResponse(workoutExerciseIds);
  }

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseService.deleteWorkoutExercise(id);
  }

  public PostWorkoutSetResponse postWorkoutSet(List<PostWorkoutSetRequest> postWorkoutSetRequests) {
    List<UUID> workoutExerciseIds = new ArrayList<>();
    postWorkoutSetRequests.forEach(postWorkoutExerciseRequest -> {
      workoutExerciseIds.add(postWorkoutSet.execute(
          postWorkoutExerciseRequest.getId(),
          postWorkoutExerciseRequest.getRepetitions(),
          postWorkoutExerciseRequest.getWeight(),
          postWorkoutExerciseRequest.getSetNumber(),
          postWorkoutExerciseRequest.getRepetitionMaximum(),
          postWorkoutExerciseRequest.getSingle(),
          postWorkoutExerciseRequest.getWorkoutExerciseId()
      ));
    });
    return new PostWorkoutSetResponse(workoutExerciseIds);  }

  public Boolean deleteWorkoutSet(UUID workoutExerciseId, UUID id) {
    return deleteWorkoutSet.execute(workoutExerciseId, id);
  }
}
