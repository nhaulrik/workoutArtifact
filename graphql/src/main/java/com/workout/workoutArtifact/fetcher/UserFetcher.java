package com.workout.workoutArtifact.fetcher;


import com.workout.workoutArtifact.dto.UserDto;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.dto.mapper.UserDtoMapper;
import com.workout.workoutArtifact.mysql.entity.UserEntity;
import com.workout.workoutArtifact.mysql.mapper.UserSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.UserJpaRepository;
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
