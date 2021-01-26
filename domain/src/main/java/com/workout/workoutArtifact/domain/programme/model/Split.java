package com.workout.workoutArtifact.domain.programme.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Split {

  private final UUID id;
  private final UUID phaseId;
  private final Integer number;
  private final Integer week;
  private final DayOfWeek dayOfWeek;
  private final LocalDateTime creationDateTime;
  private final List<ProgrammeExercise> programmeExercises;


  private Split(UUID id, UUID phaseId, Integer number, Integer week, DayOfWeek dayOfWeek, LocalDateTime creationDateTime, List<ProgrammeExercise> programmeExercises) {
    this.id = id;
    this.phaseId = phaseId;
    this.number = number;
    this.week = week;
    this.dayOfWeek = dayOfWeek;
    this.creationDateTime = creationDateTime;
    this.programmeExercises = programmeExercises;
  }

  public static Split instantiate(UUID id, UUID phaseId, Integer number, Integer week, DayOfWeek dayOfWeek, LocalDateTime creationDateTime, List<ProgrammeExercise> programmeExercises) {
    return new Split(
        id,
        phaseId, number,
        week,
        dayOfWeek,
        creationDateTime,
        programmeExercises
    );
  }
}

