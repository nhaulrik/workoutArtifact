package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.mysql.repository.SessionJpaRepository;
import com.workout.workoutArtifact.user.User;
import com.workout.workoutArtifact.mysql.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

  private final SessionJpaRepository sessionJpaRepository;

  public User toDomainObject(UserEntity userEntity) {
    return User.fromEntity(
        userEntity.getId(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getBirthDay(),
        User.Gender.valueOf(userEntity.getGender())
    );
  }

  public UserEntity toEntity(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setBirthDay(user.getBirthday());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setGender(user.getGender().name());
    userEntity.setId(user.getId());
    userEntity.setSessionEntities(sessionJpaRepository.findAllByUserEntityId(user.getId().toString()));
    return userEntity;
  }

}
