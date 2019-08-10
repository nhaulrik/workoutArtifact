package com.workout.workoutArtifact.endpoint.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private ExerciseFacade exerciseFacade;

  @Autowired
  private ExerciseController exerciseController;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(exerciseController, "exerciseFacade", exerciseFacade);
  }

  @Test
  public void addExerciseIsOk() throws Exception {
    mockMvc.perform(
        post("/workoutentity/addexercises")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("[\n"
                + "\t{\"name\":\"BARBELL_CHEST_PRESS\", \"isMultiJoint\":\"true\"}\n"
                + "]"))
        .andExpect(status().isOk());
  }

}
