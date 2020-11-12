package com.workout.workoutArtifact.domain.mysqldatabase.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification.SearchCriteria;
import com.workout.workoutArtifact.domain.mysqldatabase.configuration.JpaConfig;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource("classpath:application.properties")
@Transactional
public class WorkoutSetEntityTest {

  @Autowired
  WorkoutSetJpaRepository workoutSetRepository;

  @Resource
  ExerciseJpaRepository exerciseRepository;

  ExerciseEntity mockedExerciseEntity;

  @Before
  public void before() {
    mockedExerciseEntity = new ExerciseEntity();
    mockedExerciseEntity.setIsCompound(true);
    mockedExerciseEntity.setName("TEST_EXERCISE");
    exerciseRepository.save(mockedExerciseEntity);
  }

  @Test
  public void emptyRepositoryShouldReturnEmptyList() {

    List<WorkoutSetEntity> resultList = workoutSetRepository.findAll();
    assertThat(resultList.isEmpty(), is(true));
  }

  @Test
  public void saveAndFindWorkoutSetEntity() {
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setId(1L);
    workoutSetEntity.setRepetitions(8);
    workoutSetEntity.setWeight(80);
    workoutSetEntity.setRepetitionMaximum(12);
    workoutSetEntity.setSetNumber(1);
    workoutSetEntity.setSingle(false);

    workoutSetRepository.save(workoutSetEntity);

    WorkoutSetSpecification workoutSetSpecification = new WorkoutSetSpecification(
        new SearchCriteria("repetitions", ":", workoutSetEntity.getRepetitions()));

    List<WorkoutSetEntity> resultList = workoutSetRepository.findAll(workoutSetSpecification);

    MatcherAssert.assertThat(resultList.get(0).getId(), Matchers.is(workoutSetEntity.getId()));
  }

}
