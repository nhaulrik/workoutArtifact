package com.workout.workoutArtifact.user;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;

interface UserRepository {

  List<User> getUsers(Specification<User> userSpecification);

  UUID save(User user);
}
