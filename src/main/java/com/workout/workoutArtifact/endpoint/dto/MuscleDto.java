package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
public class MuscleDto {

  @NonNull
  private String name;

  @NonNull
  private Long id;


  @Value
  public static class IdsSpecification extends AbstractSpecification<MuscleDto> {
    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(MuscleDto muscleDto) {
      return ids.contains(muscleDto.getId());
    }
  }

  @Value
  public static class NameSpecification extends AbstractSpecification<MuscleDto> {
    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(MuscleDto muscleDto) {
      return names.contains(muscleDto.getName());
    }
  }

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<MuscleDto> {
    private final Long exerciseId;

    @Override
    public boolean isSatisfiedBy(MuscleDto muscleDto) {
      return true; // TODO: 02-09-2019 revisit this
    }
  }
}
