package com.workout.workoutArtifact.graphql.fetcher;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.SessionEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionFetcher {

  private final SessionEntityRepository sessionEntityRepository;


}
