package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
  private UUID id;

  public enum Gender{
    MALE,
    FEMALE
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<User> {

    private final List<UUID> ids;

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
