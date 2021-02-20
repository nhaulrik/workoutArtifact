package com.workout.workoutArtifact.endpoint.request.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PostUserRequest {

  private final UUID id;
  @NonNull
  private final String firstName;
  @NonNull
  private final String lastName;
  @NonNull
  private final String gender;
  @NonNull
  private final String birthday;

  public LocalDate getBirthday() {
    if (this.birthday == null) {
      return LocalDate.now();
    } else {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate parsedDate = LocalDate.parse(birthday, dateTimeFormatter);
      return parsedDate;
    }
  }

}
