package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class User {

  private String firstName;
  private String lastName;
  private LocalDate birthDay;
  private Gender gender;
  private Long id;

  public enum Gender{
    MALE,
    FEMALE
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<User> {

    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(User user) {
      return ids.contains(user.getId());
    }
  }

  @Value
  public static class FirstNameSpecification extends AbstractSpecification<User> {

    private final List<String> firstNames;

    @Override
    public boolean isSatisfiedBy(User user) {
      return firstNames.contains(user.getFirstName());
    }
  }

  @Value
  public static class LastNameSpecification extends AbstractSpecification<User> {

    private final List<String> lastNames;

    @Override
    public boolean isSatisfiedBy(User user) {
      return lastNames.contains(user.getLastName());
    }
  }
}