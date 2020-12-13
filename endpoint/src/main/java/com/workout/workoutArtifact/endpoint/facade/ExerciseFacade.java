package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.request.exercise.PostExerciseRequest;
import com.workout.workoutArtifact.endpoint.request.exercise.PostExerciseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFacade {

  private final ExerciseService exerciseService;

  public PostExerciseResponse postExercises(List<PostExerciseRequest> postExerciseRequests) {
    List<UUID> exerciseIds = new ArrayList<>();
    postExerciseRequests.forEach(postExerciseRequest -> {
      exerciseIds.add(exerciseService.postExercise(
          postExerciseRequest.getId(),
          postExerciseRequest.getName(),
          postExerciseRequest.getBodyPart(),
          postExerciseRequest.getMuscleIds()
      ));
    });
    return new PostExerciseResponse(exerciseIds);
  }
}
