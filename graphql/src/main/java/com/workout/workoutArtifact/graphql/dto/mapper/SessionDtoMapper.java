package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SessionDtoMapper {

  public SessionDto toDto(SessionEntity sessionEntity) {
    return new SessionDto(
        sessionEntity.getId(),
        sessionEntity.getLocation(),
        sessionEntity.getProgramme(),
        sessionEntity.getSplitName(),
        sessionEntity.getCreationDateTime(),
        sessionEntity.getUserEntity().getId()
    );
  }
}
