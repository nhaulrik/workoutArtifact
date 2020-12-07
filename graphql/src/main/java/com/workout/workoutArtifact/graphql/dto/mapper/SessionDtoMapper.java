package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SessionDtoMapper {

//  public Session toDomainObject(SessionDto sessionDto) {
//    Session session = new Session(sessionDto.getId());
//    session.setCreationDateTime(sessionDto.getLocalDateTime());
//    session.setProgramme(sessionDto.getProgramme());
//    session.setSplitName(sessionDto.getSplitName());
//    session.setLocation(sessionDto.getLocation());
//    return session;
//  }

  public SessionDto toDto(SessionEntity sessionEntity) {
    return new SessionDto(
        sessionEntity.getId(),
        sessionEntity.getLocation(),
        sessionEntity.getProgramme(),
        sessionEntity.getSplitName(),
        sessionEntity.getCreationDateTime()
    );
  }
}
