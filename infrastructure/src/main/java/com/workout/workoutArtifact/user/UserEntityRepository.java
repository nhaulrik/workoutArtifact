package com.workout.workoutArtifact.user;

import com.workout.workoutArtifact.mysql.entity.UserEntity;
import com.workout.workoutArtifact.mysql.mapper.UserEntityMapper;
import com.workout.workoutArtifact.mysql.mapper.UserSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.UserJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserEntityRepository implements UserRepository {


  private final UserJpaRepository userJpaRepository;
  private final UserSpecificationMapper userSpecificationMapper;
  private final UserEntityMapper userEntityMapper;

  @Override
  public List<User> getUsers(Specification<User> userSpecification) {

    org.springframework.data.jpa.domain.Specification<UserEntity> jpaSpecification = userSpecificationMapper.toJpaSpecification(userSpecification);

    return userJpaRepository.findAll(jpaSpecification).stream()
        .map(userEntityMapper::toDomainObject)
        .filter(userSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public UUID save(User user) {
    UserEntity userEntity = userEntityMapper.toEntity(user);
    return userJpaRepository.save(userEntity).getId();
  }
}
