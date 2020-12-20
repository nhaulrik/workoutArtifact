package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.request.user.PostBodyMeasurementsRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


  @PostMapping(consumes = "application/json", produces = "application/json")
  public Boolean postSessions(@RequestBody List<PostBodyMeasurementsRequest> postBodyMeasurementsRequests) {
    return null;
  }

}
