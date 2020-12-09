package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.domain.specification.Specification;
import java.util.List;
import java.util.UUID;

public interface UserRepository {

  List<User> getUsers(Specification<User> userSpecification);
  String addUser(User user);

  UUID save(User user);
}
