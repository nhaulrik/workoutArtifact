package com.workout.workoutArtifact.graphql.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private String gender;

}
