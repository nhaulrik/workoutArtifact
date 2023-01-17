package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.polar.entity.PolarSession;
import com.workout.workoutArtifact.polar.entity.PolarSession.HeartRate;
import com.workout.workoutArtifact.session.Session;
import com.workout.workoutArtifact.session.Sport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PolarSessionMapper {

  private final static String DURATION_PATTERN = "";

  public Session toDomain(PolarSession polarSession) {

    Optional<HeartRate> heartRateOptional = Optional.ofNullable(polarSession.getHeart_rate());

    Session session = Session.fromExternal(
        polarSession.getId(),
        getDuration(polarSession.getDuration()),
        Sport.valueOf(polarSession.getDetailed_sport_info()),
        Integer.valueOf(Optional.ofNullable(polarSession.getCalories()).orElse("0")),
        LocalDateTime.parse(polarSession.getStart_time()),
        heartRateOptional.isPresent() ? heartRateOptional.get().getAverage() : null,
        heartRateOptional.isPresent() ? heartRateOptional.get().getMaximum() : null
    );

    return session;
  }

  private Duration getDuration(String stringDuration) {

    Duration duration = Duration.parse(stringDuration);
    return duration;
  }

}
