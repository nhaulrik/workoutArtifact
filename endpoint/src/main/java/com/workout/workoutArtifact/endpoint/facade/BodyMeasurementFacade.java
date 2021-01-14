package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.bodymeasurement.service.BodyMeasurementService;
import com.workout.workoutArtifact.endpoint.request.user.PostBodyMeasurementsRequest;
import com.workout.workoutArtifact.endpoint.request.user.PostBodyMeasurementsResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BodyMeasurementFacade {

  private final BodyMeasurementService bodyMeasurementService;

  public PostBodyMeasurementsResponse postBodyMeasurements(List<PostBodyMeasurementsRequest> postBodyMeasurementsRequests) {
    List<UUID> bodyMeasureMentsIds = new ArrayList<>();
    postBodyMeasurementsRequests.forEach(postBodyMeasurementsRequest -> {

      if (
          postBodyMeasurementsRequest.getSingleBiceps() != null ||
              postBodyMeasurementsRequest.getSingleCalve() != null ||
              postBodyMeasurementsRequest.getSingleForearm() != null ||
              postBodyMeasurementsRequest.getSingleThigh() != null
      ) {
        bodyMeasureMentsIds.add(bodyMeasurementService.postBodyMeasurements(
            postBodyMeasurementsRequest.getId(),
            postBodyMeasurementsRequest.getUserId(),
            postBodyMeasurementsRequest.getDate(),
            postBodyMeasurementsRequest.getWeight(),
            postBodyMeasurementsRequest.getChest(),
            postBodyMeasurementsRequest.getHip(),
            postBodyMeasurementsRequest.getStomach(),
            postBodyMeasurementsRequest.getSingleThigh(),
            postBodyMeasurementsRequest.getSingleThigh(),
            postBodyMeasurementsRequest.getSingleCalve(),
            postBodyMeasurementsRequest.getSingleCalve(),
            postBodyMeasurementsRequest.getSingleBiceps(),
            postBodyMeasurementsRequest.getSingleBiceps(),
            postBodyMeasurementsRequest.getSingleBiceps(),
            postBodyMeasurementsRequest.getSingleBiceps()
        ));
      } else {
        bodyMeasureMentsIds.add(bodyMeasurementService.postBodyMeasurements(
            postBodyMeasurementsRequest.getId(),
            postBodyMeasurementsRequest.getUserId(),
            postBodyMeasurementsRequest.getDate(),
            postBodyMeasurementsRequest.getWeight(),
            postBodyMeasurementsRequest.getChest(),
            postBodyMeasurementsRequest.getHip(),
            postBodyMeasurementsRequest.getStomach(),
            postBodyMeasurementsRequest.getLeftThigh(),
            postBodyMeasurementsRequest.getRightThigh(),
            postBodyMeasurementsRequest.getLeftCalve(),
            postBodyMeasurementsRequest.getRightCalve(),
            postBodyMeasurementsRequest.getLeftBiceps(),
            postBodyMeasurementsRequest.getRightBiceps(),
            postBodyMeasurementsRequest.getLeftForearm(),
            postBodyMeasurementsRequest.getRightForearm()
        ));
      }
    });
    return new PostBodyMeasurementsResponse(bodyMeasureMentsIds);
  }

}
