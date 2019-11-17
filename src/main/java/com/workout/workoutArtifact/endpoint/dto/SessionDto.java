package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class SessionDto {

  private Long id;
  private String location;
  private String programme;
  private String splitName;
  private List<Long> workoutSetIds;
  private LocalDateTime localDateTime;

  // TODO: 14-09-2019 implement fields for time


  @Value
  public static class SplitNameSpecification extends AbstractSpecification<SessionDto> {

    private final String splitName;

    @Override
    public boolean isSatisfiedBy(SessionDto sessionDto) {
      return splitName.equals(sessionDto.getSplitName());
    }
  }

  @Value
  public static class ProgrammeSpecification extends AbstractSpecification<SessionDto> {

    private final String programme;

    @Override
    public boolean isSatisfiedBy(SessionDto sessionDto) {
      return programme.equals(sessionDto.getProgramme());
    }
  }

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
