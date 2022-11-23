package com.workout.workoutArtifact.user;

import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.user.User.Gender;
import com.workout.workoutArtifact.user.User.IdsSpecification;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getUsers(Specification<User> userSpecification) {
    return userRepository.getUsers(userSpecification);
  }


  public UUID postUser(UUID id, String firstName, String lastName, LocalDate birthday, String gender) {

    Optional<User> userOptional = userRepository.getUsers(new IdsSpecification(Arrays.asList(id))).stream().findFirst();

    if (userOptional.isPresent()) {
      User user = userOptional.get();
      user.updateFirstName(firstName);
      user.updateLastName(lastName);
      user.updateBirthday(birthday);
      user.updateGender(Gender.valueOf(gender.toUpperCase()));

      return userRepository.save(user);
    }
    throw new RuntimeException(String.format("user with id %s was not found", id));
  }

  public UUID createUser(String firstName, String lastName, LocalDate birthday, String gender) {

    User user = User.createNewUser(
        firstName,
        lastName,
        birthday,
        Gender.valueOf(gender.toUpperCase())
    );
    return userRepository.save(user);
  }
}
