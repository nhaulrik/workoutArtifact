package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Builder
@Data
public class Session {

  private UUID id;

  @NonNull
  private LocalDateTime creationDateTime;

  @NonNull
  private String location;

  @NonNull
  private String programme;

  @NonNull
  private String splitName;

  private List<Long> workoutSetIds;
  private UUID userId;

  @Value
  public static class SplitNameSpecification extends AbstractSpecification<Session> {

    private final String splitName;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return splitName.equals(session.getSplitName());
    }
  }


  @Value
  public static class ProgrammeSpecification extends AbstractSpecification<Session> {

    private final String programme;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return programme.equals(session.getProgramme());
    }
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<Session> {

    private final List<UUID> ids;

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

  @Value
  public static class UserIdSpecification extends AbstractSpecification<Session> {

    private final UUID userId;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return userId.equals(session.getUserId());
    }
  }

  @Value
  public static class DateTimeSpecification extends AbstractSpecification<Session> {

    private final LocalDateTime localDateTime;

    @Override
    public boolean isSatisfiedBy(Session session) {
      return localDateTime.getMonth().equals(session.getCreationDateTime().getMonth())
          && localDateTime.getDayOfMonth() == session.getCreationDateTime().getDayOfMonth()
          && localDateTime.getYear() == session.getCreationDateTime().getYear();
    }
  }

}
