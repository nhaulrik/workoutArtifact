package com.workout.workoutArtifact.common;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum MuscleEnum {

  CHEST("CHEST"),
  SHOULDER("SHOULDER"),
  REAR_DELT("REAR DELT");

  private String value;

  public String getValue() {
    return value;
  }

  private static final Map<String, MuscleEnum> valueMap = Arrays.stream(MuscleEnum.values())
      .collect(Collectors.toMap(e -> e.getValue(), e -> e));

  public static boolean contains(String value) {

    MuscleEnum result = valueMap.get(value);
    if (result != null) {
      return true;
    } else {
      return false;
    }
  }

  MuscleEnum(String value) {
    this.value = value;
  }
}
