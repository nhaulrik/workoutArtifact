//package com.workout.workoutArtifact.endpoint.mapper.specification;
//
//import com.workout.workoutArtifact.domain.muscle.model.Muscle;
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.AndSpecification;
//import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.domain.specification.NotSpecification;
//import com.workout.workoutArtifact.domain.specification.OrSpecification;
//import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MuscleDtoSpecificationMapper {
//
//  public AbstractSpecification<Muscle> toMuscleSpecification(AbstractSpecification<MuscleDto> muscleDtoSpecification) {
//
//    if (muscleDtoSpecification instanceof AndSpecification) {
//      return ((AndSpecification<MuscleDto>) muscleDtoSpecification).getSet().stream().map(this::toMuscleSpecification).reduce(AbstractSpecification::and).orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (muscleDtoSpecification instanceof OrSpecification) {
//      return ((OrSpecification<MuscleDto>) muscleDtoSpecification).getSet().stream().map(this::toMuscleSpecification).reduce(AbstractSpecification::or).orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (muscleDtoSpecification instanceof NotSpecification) {
//      return new NotSpecification<>(toMuscleSpecification(((NotSpecification<MuscleDto>) muscleDtoSpecification).getSpecification()));
//    } else if (muscleDtoSpecification instanceof MuscleDto.NameSpecification) {
//      return new Muscle.NameSpecification(((MuscleDto.NameSpecification) muscleDtoSpecification).getNames());
//    } else if (muscleDtoSpecification instanceof MuscleDto.IdsSpecification) {
//      return new Muscle.IdsSpecification(((MuscleDto.IdsSpecification) muscleDtoSpecification).getIds());
//    }
//    return new MatchAllSpecification();
//  }
//
//}
