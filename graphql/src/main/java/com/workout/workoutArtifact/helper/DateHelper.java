package com.workout.workoutArtifact.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

  public static LocalDateTime parseDateFromString(String dateString) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString, dateTimeFormatter);

    LocalDateTime localDateTime = localDate.atStartOfDay();

    return localDateTime;
  }


}
