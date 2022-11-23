package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.SessionDto;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
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
