package com.workout.workoutArtifact.graphql.dto;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@Builder
public class MuscleDto {

  @NonNull
  private UUID id;

  @NonNull
  private String name;

  @NonNull
  private String bodyPart;

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

}
