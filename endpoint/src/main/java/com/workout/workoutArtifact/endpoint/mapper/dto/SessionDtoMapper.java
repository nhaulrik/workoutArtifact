package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionDtoMapper {

  private final WorkoutSetDtoMapper workoutSetDtoMapper;
  private final UserDtoMapper userDtoMapper;

  public Session toDomainObject(SessionDto sessionDto) {
    return Session.builder()
        .id(sessionDto.getId() != null ? sessionDto.getId() : null)
        .programme(sessionDto.getProgramme())
        .splitName(sessionDto.getSplitName())
        .location(sessionDto.getLocation())
        .workoutSet(sessionDto.getWorkoutSetDtos().stream().map(workoutSetDtoMapper::toDomain).collect(Collectors.toList()))
        .creationDateTime(sessionDto.getLocalDateTime() != null ? sessionDto.getLocalDateTime() : LocalDateTime.now())
        .user(userDtoMapper.toDomainObject(sessionDto.getUserDto()))
        .build();
  }

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .programme(session.getProgramme())
        .splitName(session.getSplitName())
        .id(session.getId())
        .userDto(userDtoMapper.toDto(session.getUser()))
        .location(session.getLocation())
        .localDateTime(session.getCreationDateTime())
        .workoutSetDtos(session.getWorkoutSet().stream().map(workoutSetDtoMapper::toDto).collect(Collectors.toList()))
        .build();
  }
}
