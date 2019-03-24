package com.workout.workoutArtifact.backend.mysqldatabase.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.endpoint.specification.ExerciseSpecification;
import com.workout.workoutArtifact.endpoint.specification.ExerciseSpecification.SearchCriteria;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseEntityTest {

  @Autowired
  ExerciseRepository exerciseRepository;

  @Test
  public void saveAndFindExerciseEntity() {

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setIsMultiJoint(true);
    exerciseEntity.setName("TEST_EXERCISE");

    exerciseRepository.save(exerciseEntity);

    ExerciseSpecification exerciseSpecification = new ExerciseSpecification(new SearchCriteria("name", ":", exerciseEntity.getName()));

    List<ExerciseEntity> resultList = exerciseRepository.findAll(exerciseSpecification);
    assertThat(resultList.get(0), is(exerciseEntity));
  }

}
