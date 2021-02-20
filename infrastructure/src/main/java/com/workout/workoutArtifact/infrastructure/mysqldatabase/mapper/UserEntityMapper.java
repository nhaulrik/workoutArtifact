package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
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
