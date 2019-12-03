package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.UserRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.UserMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.UserSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.UserJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserEntityRepository implements UserRepository {


  private final UserJpaRepository userJpaRepository;
  private final UserMapper userMapper;
  private final UserSpecificationMapper userSpecificationMapper;

  @Override
  public List<User> getUsers(Specification<User> userSpecification) {

    org.springframework.data.jpa.domain.Specification<UserEntity> jpaSpecification = userSpecificationMapper.toJpaSpecification(userSpecification);

    return userJpaRepository.findAll(jpaSpecification).stream()
        .map(userMapper::toDomainObject)
        .filter(userSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public String addUser(User user) {

    UserEntity userEntity = userMapper.toEntity(user);
    return userJpaRepository.save(userEntity).getFirstName();
  }
}
