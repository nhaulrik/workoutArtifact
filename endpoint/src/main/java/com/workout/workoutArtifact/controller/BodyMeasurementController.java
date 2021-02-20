package com.workout.workoutArtifact.controller;

import com.workout.workoutArtifact.facade.BodyMeasurementFacade;
import com.workout.workoutArtifact.request.user.PostBodyMeasurementsRequest;
import com.workout.workoutArtifact.request.user.PostBodyMeasurementsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/bodymeasurement")
@RequiredArgsConstructor
public class BodyMeasurementController {

  private final BodyMeasurementFacade bodyMeasurementFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public PostBodyMeasurementsResponse postBodyMeasurements(@RequestBody List<PostBodyMeasurementsRequest> postBodyMeasurementsRequests) {
    return bodyMeasurementFacade.postBodyMeasurements(postBodyMeasurementsRequests);
  }

}
