package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class SessionDto {

  private Long id;
  private String location;

  // TODO: 14-09-2019 implement fields for time


  @Value
  public static class IdsSpecification extends AbstractSpecification<SessionDto> {

    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(SessionDto sessionDto) {
      return ids.contains(sessionDto.getId());
    }
  }

  @Value
  public static class LocationsSpecification extends AbstractSpecification<SessionDto> {

    private final List<String> locations;

    @Override
    public boolean isSatisfiedBy(SessionDto sessionDto) {
      return locations.contains(sessionDto.getLocation());
    }
  }
}
