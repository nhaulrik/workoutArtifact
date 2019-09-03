package com.workout.workoutArtifact.endpoint.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import com.workout.workoutArtifact.infrastructure.common.enums.MuscleEnum;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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
public class MuscleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  MuscleFacade muscleFacade;

  @Autowired
  MuscleController muscleController;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(muscleController, "muscleFacade", muscleFacade);
  }

  @Test
  public void getShoulderMusclesIsOk() throws Exception {

    List<MuscleDto> muscleDtos = Arrays.asList(new MuscleDto(MuscleEnum.FRONT_DELTS.toString(), 0L));

    doReturn(muscleDtos)
        .when(muscleFacade).getMuscles(ArgumentMatchers.any());


    mockMvc.perform(
        post("/workoutentity/getmuscles")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("[\"FRONT_DELTS\"]"))
        .andExpect(status().isOk())
        .andExpect(
            content().string(containsString(new ObjectMapper().writeValueAsString(muscleDtos))));
  }

}
