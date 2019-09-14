package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Builder
@Data
public class Session {

  private Long id;

  @NonNull
  private LocalDateTime creationDateTime;

  @NonNull
  private String location;

//  private List<WorkoutSet> workoutSets;
// TODO: 03-09-2019 at most this should be a list of workoutset ids


  @Value
  public static class IdsSpecification extends AbstractSpecification<Session> {

    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return ids.contains(session.getId());
    }
  }

  @Value
  public static class LocationsSpecification extends AbstractSpecification<Session> {

    private final List<String> locations;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return locations.contains(session.getLocation());
    }
  }

}
