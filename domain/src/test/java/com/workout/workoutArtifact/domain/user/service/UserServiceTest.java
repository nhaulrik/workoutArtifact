package com.workout.workoutArtifact.domain.user.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import specification.Specification;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class UserServiceTest {

  private final UserRepository userRepository = mock(UserRepository.class);
  private final UserService userService = new UserService(userRepository);

  @Test
  public void addUser() {

    String userAddedString = "user_added";

    User user = mock(User.class);

    doReturn(userAddedString)
        .when(userRepository).addUser(user);

    String resultString = userService.addUser(user);

    assertThat(resultString, is(equalTo(userAddedString)));
  }

  @Test
  public void getUsers() {

    Specification specification = mock(Specification.class);

    User user = mock(User.class);

    doReturn(Arrays.asList(user))
        .when(userRepository).getUsers(specification);

    List<User> users = userService.getUsers(specification);

    assertThat(users, is(equalTo(Arrays.asList(user))));
  }
}