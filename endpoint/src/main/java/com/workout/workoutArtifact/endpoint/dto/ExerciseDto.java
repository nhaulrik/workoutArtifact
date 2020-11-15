package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import io.leangen.graphql.annotations.types.GraphQLType;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Data
@GraphQLType(name = "Exercise")
@Builder
public class ExerciseDto {

  private UUID id;

  @NonNull
  private String name;

  @NonNull
  private String bodyPart;

  @NonNull
  private Boolean isCompound;

  @Value
  public static class NameSpecification extends AbstractSpecification<ExerciseDto> {

    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return names.contains(exerciseDto.getName());
    }
  }

  @Value
  public static class IsCompoundSpecification extends AbstractSpecification<ExerciseDto> {

    private final Boolean isCompound;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return isCompound.equals(exerciseDto.getIsCompound());
    }
  }

  @Value
  public static class BodyPartsSpecification extends AbstractSpecification<ExerciseDto> {

    private final List<String> bodyParts;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return bodyParts.contains(exerciseDto.getBodyPart());
    }
  }

  @Value
  public static class ExerciseIdSpecification extends AbstractSpecification<ExerciseDto> {

    private final UUID exerciseId;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return exerciseDto.getId().equals(exerciseId);
    }
  }


}
