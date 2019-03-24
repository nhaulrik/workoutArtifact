package com.workout.workoutArtifact.backend.mysqldatabase.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification;
import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification.SearchCriteria;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutSetEntityTest {

  @Autowired
  WorkoutSetRepository workoutSetRepository;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Test
  public void saveAndWorkoutSetEntity() {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setIsMultiJoint(true);
    exerciseEntity.setName("TEST_EXERCISE");
    exerciseRepository.save(exerciseEntity);

    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setRepetitions(1337);
    workoutSetEntity.setSingle(true);
    workoutSetEntity.setExerciseEntity(exerciseEntity);

    workoutSetRepository.save(workoutSetEntity);

    WorkoutSetSpecification workoutSetSpecification = new WorkoutSetSpecification(
        new SearchCriteria("repetitions", ":", workoutSetEntity.getRepetitions()));

    List<WorkoutSetEntity> resultList = workoutSetRepository.findAll(workoutSetSpecification);

    assertThat(resultList.get(0), is(workoutSetEntity));
  }

}
