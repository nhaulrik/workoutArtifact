package com.workout.workoutArtifact.endpoint.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MuscleFacadeIntegrationTest {

  @Autowired
  MuscleFacade muscleFacade;

  @Test
  public void getAllMuscles() {
    List<MuscleDto> muscleDtos = muscleFacade.getMuscles(new MatchAllSpecification());
    assertThat(muscleDtos.size(), is(17));
  }

}
