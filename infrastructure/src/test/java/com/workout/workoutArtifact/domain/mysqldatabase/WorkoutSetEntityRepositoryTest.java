//package com.workout.workoutArtifact.domain.mysqldatabase;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//import com.workout.workoutArtifact.exercise.Exercise;
//import com.workout.workoutArtifact.specification.Specification;
//import com.workout.workoutArtifact.workoutset.WorkoutSet;
//import com.workout.workoutArtifact.workoutset.WorkoutSet.ExerciseIdSpecification;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.WorkoutSetEntityRepository;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetEntityMapper;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutSetSpecificationMapper;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//
//public class WorkoutSetEntityRepositoryTest {
//
//  private final WorkoutSetJpaRepository workoutSetJpaRepository = mock(WorkoutSetJpaRepository.class);
//  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper = mock(WorkoutSetSpecificationMapper.class);
//  private final WorkoutSetEntityMapper workoutSetEntityMapper = mock(WorkoutSetEntityMapper.class);
//  private final WorkoutSetEntityRepository workoutSetEntityRepository = new WorkoutSetEntityRepository(workoutSetJpaRepository, workoutSetEntityMapper, workoutSetSpecificationMapper);
//
//  @Test
//  public void getWorkoutSet() {
//
//    UUID exerciseId = UUID.randomUUID();
//
//    Specification specification = new ExerciseIdSpecification(Arrays.asList(exerciseId));
//    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(org.springframework.data.jpa.domain.Specification.class);
//
//    WorkoutSetEntity workoutSetEntity = mock(WorkoutSetEntity.class);
//
//    WorkoutSet workoutSet = getWorkoutSetMock(mock(Exercise.class));
//
//    doReturn(jpaSpecification)
//        .when(workoutSetSpecificationMapper).toJpaSpecification(specification);
//
//    doReturn(Arrays.asList(workoutSetEntity))
//        .when(workoutSetJpaRepository).findAll(jpaSpecification);
//
//    Mockito.doReturn(workoutSet)
//        .when(workoutSetEntityMapper).toDomain(workoutSetEntity);
//
//    List<WorkoutSet> workoutSetList = workoutSetEntityRepository.getWorkoutSet(specification);
//
//    assertThat(workoutSetList.size(), is(1));
//    assertThat(workoutSetList.get(0), Matchers.is(workoutSet));
//  }
//
//  @Test
//  public void addWorkoutSet() {
//
//    WorkoutSet workoutSet = getWorkoutSetMock(mock(Exercise.class));
//    WorkoutSetEntity workoutSetEntity = mock(WorkoutSetEntity.class);
//
//    Mockito.doReturn(workoutSetEntity)
//        .when(workoutSetEntityMapper).toEntity(workoutSet);
//
//    workoutSetEntityRepository.addWorkoutSet(Arrays.asList(workoutSet));
//
//    Class<ArrayList<WorkoutSetEntity>> workoutSetEntityListClass = (Class<ArrayList<WorkoutSetEntity>>) (Class) ArrayList.class;
//    ArgumentCaptor<ArrayList<WorkoutSetEntity>> arg = ArgumentCaptor.forClass(workoutSetEntityListClass);
//    verify(workoutSetJpaRepository).saveAll(arg.capture());
//
//    assertThat(arg.getValue(), is(Arrays.asList(workoutSetEntity)));
//  }
//
//  private WorkoutSet getWorkoutSetMock(Exercise exercise) {
//    return WorkoutSet.createWorkoutSet(
//        UUID.randomUUID(),
//        exercise,
//        false,
//        0d,
//        1,
//        1,
//        1);
//  }
//}