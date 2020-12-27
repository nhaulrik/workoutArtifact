package com.workout.workoutArtifact.graphql.fetcher;


import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.graphql.dto.mapper.UserDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.UserSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.UserJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFetcher {

  private final UserJpaRepository userJpaRepository;
  private final UserSpecificationMapper userSpecificationMapper;
  private final UserDtoMapper userDtoMapper;

  public List<UserDto> getUsers(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = userSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<UserEntity> userEntities = userJpaRepository.findAll(jpaSpecification);
    List<UserDto> userDtos = userEntities.stream().map(userDtoMapper::toDto).collect(Collectors.toList());

    return userDtos;
  }
}
