package com.workout.workoutArtifact.session;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.workoutExercise.WorkoutExercise;
import com.workout.workoutArtifact.workoutset.WorkoutSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import org.springframework.util.Assert;

@Data
public class Session {

  private UUID id;
  private LocalDateTime creationDateTime;

  private String location;
  private String programme;
  private String splitName;
  private UUID userId;

  private final List<WorkoutExercise> workoutExercises = new ArrayList<>();

  private Session(UUID id, LocalDateTime creationDateTime, String location, String programme, String splitName, List<WorkoutExercise> workoutExercises, UUID userId) {
    this.id = id;
    this.creationDateTime = creationDateTime;
    this.location = location;
    this.programme = programme;
    this.splitName = splitName;
    this.workoutExercises.addAll(workoutExercises);
    this.userId = userId;
  }

  public static Session createNewSession(LocalDateTime localDateTime, String location, String programme, String splitName, UUID userId) {
    Session session = new Session(
        UUID.randomUUID(),
        localDateTime,
        location,
        programme,
        splitName,
        new ArrayList<>(),
        userId
    );
    return session;
  }

  public static Session instantiate(UUID id, LocalDateTime creationDateTime, String programme, String splitName, String location, List<WorkoutExercise> workoutExercises, UUID userId) {
    return new Session(id, creationDateTime, location, programme, splitName, workoutExercises, userId);
  }

  public List<WorkoutSet> getWorkoutSet() {
    return this.workoutExercises.stream()
        .map(we -> we.getWorkoutSets())
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  public List<WorkoutExercise> getWorkoutExercises(List<UUID> exerciseIds) {
    return this.workoutExercises.stream()
        .filter(we -> exerciseIds.contains(we.getId()))
        .collect(Collectors.toList());
  }

  public void changeLocation(String location) {
    this.location = location;
  }

  public void changeProgramme(String programme) {
    this.programme = programme;
  }

  public void changeSplitName(String splitName) {
    this.splitName = splitName;
  }

  public void changeTime(LocalDateTime localDateTime) {
    Assert.notNull(localDateTime, "localDateTime is required");
    this.creationDateTime = localDateTime;
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
  public static class UserIdsSpecification extends AbstractSpecification<Session> {

    private final List<UUID> ids;

    // TODO: 08-12-2020 this is a hack
    @Override
    public boolean isSatisfiedBy(Session session) {
      return true;
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

  @Getter
  public static class BetweenDateTimeSpecification extends AbstractSpecification<Session> {

    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public BetweenDateTimeSpecification(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
      this.fromDateTime = fromDateTime.withHour(0).withMinute(0);
      this.toDateTime = toDateTime.withHour(23).withMinute(59).withSecond(59);
    }


    @Override
    public boolean isSatisfiedBy(Session session) {
      return true;
    }
  }

}
