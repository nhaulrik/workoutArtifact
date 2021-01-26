package com.workout.workoutArtifact.domain.programme.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Split {

  private final UUID id;
  private final Integer number;
  private final Integer week;
  private final DayOfWeek dayOfWeek;
  private final LocalDateTime creationDateTime;
  private final List<ProgrammeExercise> programmeExercises;


  private Split(UUID id, Integer number, Integer week, DayOfWeek dayOfWeek, LocalDateTime creationDateTime, List<ProgrammeExercise> programmeExercises) {
    this.id = id;
    this.number = number;
    this.week = week;
    this.dayOfWeek = dayOfWeek;
    this.creationDateTime = creationDateTime;
    this.programmeExercises = programmeExercises;
  }
}

