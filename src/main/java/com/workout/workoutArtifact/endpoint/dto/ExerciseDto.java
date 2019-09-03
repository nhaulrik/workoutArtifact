package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import io.leangen.graphql.annotations.types.GraphQLType;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@GraphQLType(name = "Exercise")
public class ExerciseDto {

  @NonNull
  private Long id;

  @NonNull
  private String name;

  @NonNull
  String type;

  @NonNull
  private String bodyPart;


  @Value
  public static class NameSpecification extends AbstractSpecification<ExerciseDto> {
    private final List<String> names;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return names.contains(exerciseDto.getName());
    }
  }

  @Value
  public static class TypesSpecification extends AbstractSpecification<ExerciseDto> {
    private final List<String> types;

    @Override
    public boolean isSatisfiedBy(ExerciseDto exerciseDto) {
      return types.contains(exerciseDto.getType());
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


}
