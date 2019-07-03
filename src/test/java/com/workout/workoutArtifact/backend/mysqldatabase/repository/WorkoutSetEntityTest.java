package com.workout.workoutArtifact.backend.mysqldatabase.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.workout.workoutArtifact.backend.mysqldatabase.configuration.JpaConfig;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification.SearchCriteria;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
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
  WorkoutSetRepository workoutSetRepository;

  @Resource
  ExerciseRepository exerciseRepository;

  ExerciseEntity mockedExerciseEntity;

  @Before
  public void before() {
    mockedExerciseEntity = new ExerciseEntity();
    mockedExerciseEntity.setIsMultiJoint(true);
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
    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity(1337, 10, true, 8, 1);

    workoutSetRepository.save(workoutSetEntity);

    WorkoutSetSpecification workoutSetSpecification = new WorkoutSetSpecification(
        new SearchCriteria("repetitions", ":", workoutSetEntity.getRepetitions()));

    List<WorkoutSetEntity> resultList = workoutSetRepository.findAll(workoutSetSpecification);

    assertThat(resultList.get(0), is(workoutSetEntity));
  }

}
