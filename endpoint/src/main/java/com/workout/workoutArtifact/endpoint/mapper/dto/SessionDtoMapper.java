package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionDtoMapper {

  private final WorkoutSetDtoMapper workoutSetDtoMapper;

  public Session toDomainObject(SessionDto sessionDto) {
    return Session.builder()
        .id(sessionDto.getId() != null ? sessionDto.getId() : null)
        .programme(sessionDto.getProgramme())
        .splitName(sessionDto.getSplitName())
        .location(sessionDto.getLocation())
        .workoutSet(sessionDto.getWorkoutSetIds().stream().map(id -> WorkoutSet.builder().id(id).build()).collect(Collectors.toList()))
        .creationDateTime(sessionDto.getLocalDateTime() != null ? sessionDto.getLocalDateTime() : LocalDateTime.now())
        .user(User.builder().id(sessionDto.getUserId()).build())
        .build();
  }

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .programme(session.getProgramme())
        .splitName(session.getSplitName())
        .id(session.getId())
        .userId(session.getUser().getId())
        .location(session.getLocation())
        .localDateTime(session.getCreationDateTime())
        .workoutSetIds(session.getWorkoutSet().stream().map(WorkoutSet::getId).collect(Collectors.toList()))
        .build();
  }
}
