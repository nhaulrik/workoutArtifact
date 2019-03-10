package com.workout.workoutArtifact.endpoint.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.MuscleEnum;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class WorkoutEntityControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  MuscleFacade muscleFacade;

  @Autowired
  WorkoutEntityController workoutEntityController;


  @Before
  public void setUp() {
    ReflectionTestUtils.setField(workoutEntityController, "muscleFacade", muscleFacade);
  }


  @Test
  public void getMuscles() throws Exception {

    List<Muscle> muscles = new ArrayList<>();
    muscles.add(new Muscle(MuscleEnum.BICEPS_LONG, BodyPartEnum.ARM));

    when(muscleFacade.getMusclesByName(Arrays.asList("CHEST")))
        .thenReturn(muscles);

    mockMvc.perform(
        post("/workoutentity/getmuscles")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("[\"CHEST\"]"))
        .andExpect(status().isOk())
        .andExpect(
            content().string(containsString(new ObjectMapper().writeValueAsString(muscles))));
  }


}
