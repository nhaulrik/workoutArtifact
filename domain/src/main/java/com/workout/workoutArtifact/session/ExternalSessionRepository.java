package com.workout.workoutArtifact.session;

import java.util.List;
import java.util.UUID;

public interface ExternalSessionRepository {

  List<Session> getExternalSessions(UUID userId);

}
