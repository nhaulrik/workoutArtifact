package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.workoutExercise.service.WorkoutExerciseService;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.PostWorkoutExerciseRequest;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.PostWorkoutExerciseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFacade {

  private final WorkoutExerciseService workoutExerciseService;

  public PostWorkoutExerciseResponse postWorkoutExercises(List<PostWorkoutExerciseRequest> createWorkoutExerciseRequests) {

    List<UUID> workoutExerciseIds = new ArrayList<>();
    createWorkoutExerciseRequests.forEach(postWorkoutExerciseRequest -> {
      workoutExerciseIds.add(workoutExerciseService.postWorkoutExercise(
          postWorkoutExerciseRequest.getId(),
          postWorkoutExerciseRequest.getUserId(),
          postWorkoutExerciseRequest.getExerciseId(),
          postWorkoutExerciseRequest.getExerciseNumber(),
          postWorkoutExerciseRequest.getSessionId()
      ));
    });
    return new PostWorkoutExerciseResponse(workoutExerciseIds);
  }

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseService.deleteWorkoutExercise(id);
  }
}
