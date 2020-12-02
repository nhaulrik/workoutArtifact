package com.workout.workoutArtifact.domain.session.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;

@Data
@RequiredArgsConstructor
public class Session {

  private final UUID id;
  private LocalDateTime creationDateTime;

  private String location;
  private String programme;
  private String splitName;
  private UUID userId;

  private final List<WorkoutExercise> workoutExercises = new ArrayList<>();

  public void changeLocation(String location) {
    Assert.notNull(location, "location is required");
    this.location = location;
  }

  public static Session createNewSession(UUID userId, LocalDateTime localDateTime) {
    Session session = new Session(UUID.randomUUID());
    session.creationDateTime = localDateTime;
    session.userId = userId;
    return session;
  }

  public void changeProgramme(String programme) {
    Assert.isTrue(!Strings.isBlank(programme), "programme is required");
    this.programme = programme;
  }

  public void changeSplitName(String splitName) {
    Assert.isTrue(!Strings.isBlank(splitName), "splitName is required");
    this.splitName = splitName;
  }

  public void changeTime(LocalDateTime localDateTime) {
    Assert.notNull(localDateTime, "localDateTime is required");
    this.creationDateTime = localDateTime;
  }

  public void changeUser(UUID userId) {
    this.userId = userId;
  }

  public Optional<WorkoutExercise> getWorkoutExercise(UUID id) {
    if (id == null) {
      return Optional.empty();
    }
    return this.workoutExercises.stream().filter(ws -> id.equals(ws.getId())).findFirst();
  }

  public void addWorkoutExercise(WorkoutExercise workoutExercise) {
    Assert.notNull(workoutExercise, "workoutExercise is required");

    if (this.workoutExercises.stream().filter(we -> we.getId().equals(we)).findAny().isPresent()) {
      throw new RuntimeException(String.format("workoutExercise with id: %s is already present on session with id: %s", workoutExercise.getId().toString(), this.id.toString()));
    }
    this.workoutExercises.add(workoutExercise);
  }

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
