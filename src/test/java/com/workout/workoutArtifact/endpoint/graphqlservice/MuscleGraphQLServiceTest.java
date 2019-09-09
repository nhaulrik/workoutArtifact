package com.workout.workoutArtifact.endpoint.graphqlservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.workout.workoutArtifact.endpoint.controller.GraphQLController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MuscleGraphQLServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  GraphQLController graphQLController;


  @Test
  public void graphql() throws Exception {

// TODO: 03-09-2019 make some query that uses a nested query
    mockMvc.perform(
        post("/graphql")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("{\"query\":\"query {\\n  exercises {\\n    name\\n  \\tmuscles {\\n      "
                + "name\\n    }\\n  }\\n}\",\"variables\":null}"))
        .andExpect(status().isOk());
//        .andExpect(content().string(containsString(new ObjectMapper().writeValueAsString(result))));
  }
}