package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;

public interface UserRepository {

  List<User> getUsers(Specification<User> userSpecification);
  String addUser(User user);
}
