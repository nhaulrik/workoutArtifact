package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.workoutExercise.service.WorkoutExerciseService;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.CreateWorkoutExerciseRequest;
import com.workout.workoutArtifact.endpoint.request.workoutexercise.CreateWorkoutExerciseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFacade {

  private final WorkoutExerciseService workoutExerciseService;

  public CreateWorkoutExerciseResponse createWorkoutExercises(List<CreateWorkoutExerciseRequest> createWorkoutExerciseRequests) {

    List<UUID> workoutExerciseIds = new ArrayList<>();
    createWorkoutExerciseRequests.forEach(postWorkoutExerciseRequest -> {
      workoutExerciseIds.add(workoutExerciseService.createWorkoutExercise(
          postWorkoutExerciseRequest.getId(),
          postWorkoutExerciseRequest.getExerciseId(),
          postWorkoutExerciseRequest.getExerciseNumber(),
          postWorkoutExerciseRequest.getSessionId()
      ));
    });
    return new CreateWorkoutExerciseResponse(workoutExerciseIds);
  }

  public Boolean deleteWorkoutExercise(UUID id) {
    return workoutExerciseService.deleteWorkoutExercise(id);
  }
}
