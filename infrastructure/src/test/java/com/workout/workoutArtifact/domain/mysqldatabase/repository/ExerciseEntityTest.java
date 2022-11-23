//package com.workout.workoutArtifact.domain.mysqldatabase.repository;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.domain.mysqldatabase.configuration.JpaConfig;
//import com.workout.workoutArtifact.domain.mysqldatabase.specification.ExerciseSpecification;
//import com.workout.workoutArtifact.domain.mysqldatabase.specification.ExerciseSpecification.SearchCriteria;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.transaction.Transactional;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {JpaConfig.class}, loader = AnnotationConfigContextLoader.class)
//@TestPropertySource("classpath:application.properties")
//@Transactional
//public class ExerciseEntityTest {
//
//  @Resource
//  ExerciseJpaRepository exerciseRepository;
//
//  @Test
//  public void saveAndFindExerciseEntity() {
//
//    ExerciseEntity exerciseEntity = new ExerciseEntity();
//    exerciseEntity.setIsCompound(true);
//    exerciseEntity.setName("TEST_EXERCISE");
//
//    exerciseRepository.save(exerciseEntity);
//
//    ExerciseSpecification exerciseSpecification = new ExerciseSpecification(new SearchCriteria("name", ":", exerciseEntity.getName()));
//
//    List<ExerciseEntity> resultList = exerciseRepository.findAll(exerciseSpecification);
//    assertThat(resultList.get(0), Matchers.is(exerciseEntity));
//  }
//
//}
