package com.workout.workoutArtifact.domain.programme.service;

import com.workout.workoutArtifact.domain.programme.model.ProgrammeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgrammeService {

  private final ProgrammeRepository programmeRepository;


}
