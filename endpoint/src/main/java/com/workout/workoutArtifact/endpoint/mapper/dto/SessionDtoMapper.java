package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionDtoMapper {

  public Session toDomainObject(SessionDto sessionDto) {
    return Session.builder()
        .id(sessionDto.getId() != null ? sessionDto.getId() : null)
        .programme(sessionDto.getProgramme())
        .splitName(sessionDto.getSplitName())
        .location(sessionDto.getLocation())
        .workoutSetIds(sessionDto.getWorkoutSetIds())
        .creationDateTime(sessionDto.getLocalDateTime() != null ? sessionDto.getLocalDateTime() : LocalDateTime.now())
        .userId(sessionDto.getUserId())
        .build();
  }

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .programme(session.getProgramme())
        .splitName(session.getSplitName())
        .id(session.getId())
        .userId(session.getUserId())
        .location(session.getLocation())
        .localDateTime(session.getCreationDateTime())
        .workoutSetIds(session.getWorkoutSetIds())
        .build();
  }
}
