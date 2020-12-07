//package com.workout.workoutArtifact.endpoint.facade;
//
//import com.workout.workoutArtifact.domain.muscle.model.Muscle;
//import com.workout.workoutArtifact.domain.muscle.service.MuscleService;
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.Specification;
//import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
//import com.workout.workoutArtifact.endpoint.mapper.dto.MuscleDtoMapper;
//import com.workout.workoutArtifact.endpoint.mapper.specification.MuscleDtoSpecificationMapper;
//import java.util.List;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MuscleFacade {
//
//  private final MuscleService muscleService;
//  private final MuscleDtoMapper muscleDtoMapper;
//  private final MuscleDtoSpecificationMapper muscleDtoSpecificationMapper;
//
//  public String addMuscles(List<MuscleDto> muscleDtos) {
//    return muscleService.addMuscles(muscleDtos.stream()
//        .map(muscleDtoMapper::toDomainObject)
//        .collect(Collectors.toList()));
//  }
//
//  public List<MuscleDto> getMuscles(AbstractSpecification<MuscleDto> specification) {
//
//    Specification<Muscle> muscleSpecification = muscleDtoSpecificationMapper.toMuscleSpecification(specification);
//
//    return muscleService.getMuscles(muscleSpecification).stream()
//        .filter(muscleSpecification::isSatisfiedBy)
//        .map(muscleDtoMapper::toDto)
//        .collect(Collectors.toList());
//  }
//
//}
