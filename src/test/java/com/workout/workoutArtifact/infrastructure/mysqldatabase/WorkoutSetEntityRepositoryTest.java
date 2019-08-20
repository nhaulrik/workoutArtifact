package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet.ExerciseNameSpecification;
import com.workout.workoutArtifact.infrastructure.common.mapper.WorkoutSetMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper
    .WorkoutSetSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class WorkoutSetEntityRepositoryTest {

  private final WorkoutSetJpaRepository workoutSetJpaRepository = mock(WorkoutSetJpaRepository.class);
  private final WorkoutSetSpecificationMapper workoutSetSpecificationMapper = mock(WorkoutSetSpecificationMapper.class);
  private final WorkoutSetMapper workoutSetMapper = mock(WorkoutSetMapper.class);
  private final WorkoutSetEntityRepository workoutSetEntityRepository = new WorkoutSetEntityRepository(workoutSetJpaRepository, workoutSetMapper, workoutSetSpecificationMapper);

  @Test
  public void getWorkoutSet() {

    String exerciseName = "some_exercise_name";

    Specification specification = new ExerciseNameSpecification(Arrays.asList(exerciseName));
    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(org.springframework.data.jpa.domain.Specification.class);

    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(0,0d, false, 0, 0);

    WorkoutSet workoutSet = getWorkoutSetMock(exerciseName);

    doReturn(jpaSpecification)
        .when(workoutSetSpecificationMapper).toJpaSpecification(specification);

    doReturn(Arrays.asList(workoutSetEntity))
        .when(workoutSetJpaRepository).findAll(jpaSpecification);

    doReturn(workoutSet)
        .when(workoutSetMapper).toDomain(workoutSetEntity);

    List<WorkoutSet> workoutSetList = workoutSetEntityRepository.getWorkoutSet(specification);

    assertThat(workoutSetList.size(), is(1));
    assertThat(workoutSetList.get(0), is(workoutSet));
  }

  @Test
  public void addWorkoutSet() {

    WorkoutSet workoutSet = getWorkoutSetMock("");
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(0,0d, false, 0, 0);

    doReturn(workoutSetEntity)
        .when(workoutSetMapper).toEntity(workoutSet);

    workoutSetEntityRepository.addWorkoutSet(Arrays.asList(workoutSet));

    Class<ArrayList<WorkoutSetEntity>> workoutSetEntityListClass = (Class<ArrayList<WorkoutSetEntity>>) (Class) ArrayList.class;
    ArgumentCaptor<ArrayList<WorkoutSetEntity>> arg = ArgumentCaptor.forClass(workoutSetEntityListClass);
    verify(workoutSetJpaRepository).saveAll(arg.capture());

    assertThat(arg.getValue(), is(Arrays.asList(workoutSetEntity)));
  }

  private WorkoutSet getWorkoutSetMock(String exerciseName) {
    return WorkoutSet.builder()
        .exerciseName(exerciseName)
        .exercise(mock(Exercise.class))
        .repetitionMaximum(0)
        .repetitions(1)
        .weight(0d)
        .setNumber(1)
        .single(false)
        .build();
  }
}