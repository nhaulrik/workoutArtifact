package com.workout.workoutArtifact.programme;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Split {

  private final UUID id;
  private final UUID phaseId;
  private final Integer number;
  private final String name;
  private final Integer week;
  private final DayOfWeek dayOfWeek;
  private final LocalDateTime creationDateTime;
  private final List<SplitExercise> splitExercises;


  private Split(UUID id, UUID phaseId, Integer number, Integer week, String name, DayOfWeek dayOfWeek, LocalDateTime creationDateTime, List<SplitExercise> splitExercises) {
    this.id = id;
    this.phaseId = phaseId;
    this.number = number;
    this.week = week;
    this.name = name;
    this.dayOfWeek = dayOfWeek;
    this.creationDateTime = creationDateTime;
    this.splitExercises = splitExercises;
  }

  public static Split instantiate(UUID id, UUID phaseId, Integer number, Integer week, String name, DayOfWeek dayOfWeek, LocalDateTime creationDateTime, List<SplitExercise> splitExercises) {
    return new Split(
        id,
        phaseId, number,
        week,
        name,
        dayOfWeek,
        creationDateTime,
        splitExercises
    );
  }

  public static Split createNew(UUID phaseId, Integer number, Integer week, String name, DayOfWeek dayOfWeek, LocalDateTime creationDateTime) {
    return new Split(
        UUID.randomUUID(),
        phaseId, number,
        week,
        name,
        dayOfWeek,
        creationDateTime,
        new ArrayList<>()
    );
  }
}

