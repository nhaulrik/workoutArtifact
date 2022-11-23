package com.workout.workoutArtifact.programme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class SplitExercise {

  private final UUID id;
  private final Integer splitExerciseNumber;
  private final List<SplitExerciseSet> splitExerciseSets;


  private SplitExercise(UUID id, Integer splitExerciseNumber, List<SplitExerciseSet> splitExerciseSets) {
    this.id = id;
    this.splitExerciseNumber = splitExerciseNumber;
    this.splitExerciseSets = splitExerciseSets;
  }

  public static SplitExercise instantiate(UUID id, Integer splitExerciseNumber, List<SplitExerciseSet> splitExerciseSets) {
    return new SplitExercise(id, splitExerciseNumber, splitExerciseSets);
  }

  public static SplitExercise createNew(Integer splitExerciseNumber) {
    return new SplitExercise(UUID.randomUUID(), splitExerciseNumber, new ArrayList<>());
  }

}
