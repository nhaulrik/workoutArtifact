package com.workout.workoutArtifact.request.programme;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostProgrammeRequest {

  private final UUID id;
  private final String date;
  private final String name;
  private final String description;
  private final List<UUID> phaseIds;

  public LocalDate getDate() {

    if (this.date == null) {
      return LocalDate.now();
    } else {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);
      return parsedDate;
    }
  }

}
