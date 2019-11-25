package com.workout.workoutArtifact.domain.user.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

  private String firstName;
  private String lastName;
  private LocalDate birthDay;
  private Gender gender;

  public enum Gender{
    MALE,
    FEMALE
  }
}
