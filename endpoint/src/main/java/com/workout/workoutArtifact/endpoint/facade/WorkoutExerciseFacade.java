//package com.workout.workoutArtifact.endpoint.facade;
//
//import com.workout.workoutArtifact.domain.session.service.SessionService;
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.Specification;
//import com.workout.workoutArtifact.domain.workoutExercise.model.WorkoutExercise;
//import com.workout.workoutArtifact.domain.workoutExercise.service.WorkoutExerciseService;
//import com.workout.workoutArtifact.endpoint.dto.WorkoutExerciseDto;
//import com.workout.workoutArtifact.endpoint.mapper.dto.WorkoutExerciseDtoMapper;
//import com.workout.workoutArtifact.endpoint.mapper.specification.WorkoutExerciseDtoSpecificationMapper;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//@Component
//@RequiredArgsConstructor
//public class WorkoutExerciseFacade {
//
//  private final WorkoutExerciseService workoutExerciseService;
//  private final SessionService sessionService;
//  private final WorkoutExerciseDtoMapper workoutExerciseDtoMapper;
//  private final WorkoutExerciseDtoSpecificationMapper workoutExerciseDtoSpecificationMapper;
//
//  public List<WorkoutExerciseDto> getWorkoutExercises(AbstractSpecification<WorkoutExerciseDto> specification) {
//
//    Specification<WorkoutExercise> WorkoutExerciseSpecification = workoutExerciseDtoSpecificationMapper.toWorkoutExerciseSpecification(specification);
//    return workoutExerciseService.getWorkoutExercises(WorkoutExerciseSpecification).stream()
//        .map(workoutExerciseDtoMapper::toDto)
//        .collect(Collectors.toList());
//  }
//
//
//  public UUID postWorkoutExercise(UUID id, Integer exerciseNumber, UUID sessionId, UUID exerciseId) {
//    Assert.notNull(sessionId, "sessionId is required");
//    return sessionService.postWorkoutExercise(id, exerciseNumber, sessionId, exerciseId);
//  }
//
//  public Boolean deleteWorkoutExercise(UUID id) {
//    return workoutExerciseService.deleteWorkoutExercise(id);
//  }
//}
